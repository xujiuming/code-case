package com.ming.api.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.google.common.util.concurrent.*;
import com.ming.base.ApplicationConfig;
import com.ming.core.utils.SpringBeanManagerUtils;
import com.ming.core.utils.ThreadPoolUtils;
import com.ming.entity.image.ImageBaseInfo;
import com.ming.service.image.ImageBaseInfoService;
import org.assertj.core.util.Lists;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * aliyun oss 操作工具类
 *
 * @author ming
 * @date 2017-08-15 16点
 */
public class OssUtils {

    /**
     * 上限1000
     */
    private static final int maxKeys = 999;
    /**
     * @author ming
     * @date 2017-08-15 16点
     */
    private static OSSClient ossClient = new OSSClient(ApplicationConfig.endpoint, ApplicationConfig.accessKeyId, ApplicationConfig.accessKeySecret);

    /**
     * 没有使用优化之后的线程数
     *
     * @author ming
     * @date 2017-08-31 14点
     */
    public static void pushOssFuture() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(ThreadPoolUtils.executor);
        ListenableFuture listenableFuture = executorService.submit(() -> {
            OssUtils.pushOssImage();
        });
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object result) {
                System.out.println("刷新成功");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("失败 进入重试队列");
            }
        }, ThreadPoolUtils.executor);
    }

    /**
     * 使用异步执行
     *
     * @author ming
     * @date 2017-08-31 14点
     */
    public static Future<Boolean> pushOssAsync() {
        return CompletableFuture.supplyAsync(OssUtils::pushOssImage, ThreadPoolUtils.executor);
    }

    /**
     * 刷新oss上的图片资源 存储到mysql
     */
    public static Boolean pushOssImage() {
        List<ImageBaseInfo> imageBaseInfoList = Lists.newArrayList();
        String nextMarker = null;
        ObjectListing objectListing;
        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(ApplicationConfig.bucket).withMarker(nextMarker).withMaxKeys(maxKeys));
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            //过滤文件夹  oss把文件夹当作一个okb的对象
            SummaryToImageBaseInfo(imageBaseInfoList, sums);
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        //刷新到数据库
        SpringBeanManagerUtils.getBeanByType(ImageBaseInfoService.class).pushImageList(imageBaseInfoList);
        return Boolean.TRUE;
    }

    /**
     * @author ming
     * @date 2017-08-28 10点
     */
    private static void SummaryToImageBaseInfo(List<ImageBaseInfo> resultList, List<OSSObjectSummary> summarys) {
        for (OSSObjectSummary summary : summarys) {
            if (!summary.getKey().endsWith("/")) {
                ImageBaseInfo info = new ImageBaseInfo();
                info.setBucketName(ApplicationConfig.bucket);
                info.setName(summary.getKey());
                info.setUrl(ApplicationConfig.endpoint);
                info.setMd5(summary.getETag());
                info.setStorageClass(summary.getStorageClass());
                info.setLastModified(summary.getLastModified().getTime());
                info.setCreateTimeMillis(System.currentTimeMillis());
                resultList.add(info);
            }
        }
    }

}
