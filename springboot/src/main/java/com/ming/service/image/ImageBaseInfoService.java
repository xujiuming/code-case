package com.ming.service.image;

import com.ming.base.orm.DynamicSpecifications;
import com.ming.base.orm.PageParam;
import com.ming.entity.image.ImageBaseInfo;
import com.ming.repository.image.ImageBaseInfoRepository;
import com.ming.service.SupportService;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ImageBaseInfoService extends SupportService {
    @Autowired
    private ImageBaseInfoRepository imageBaseInfoRepository;

    /**
     * 刷新imagelist
     *
     * @author ming
     * @date 2017-08-16 22点
     */
    public void pushImageList(Collection<ImageBaseInfo> imageBaseInfoCollection) {
        //获取md5 list
        Set<String> md5Set = imageBaseInfoCollection.stream()
                .map(ImageBaseInfo::getMd5)
                .collect(Collectors.toSet());
        // 获取已经存在的md5数据
        Collection<ImageBaseInfo> imageBaseInfoList = imageBaseInfoRepository.findAllByMd5In(md5Set);
        //使用优化线程 的map
        Map<String, ImageBaseInfo> md5ImageMap = imageBaseInfoList.parallelStream()
                .collect(Collectors.toMap(ImageBaseInfo::getMd5, i -> i, (existsValue, newValue) -> newValue, ConcurrentHashMap::new));
        //将存在的数据的id 给赋值到新的info中 做更新操作
        for (ImageBaseInfo info : imageBaseInfoCollection) {
            if (md5ImageMap.containsKey(info.getMd5())) {
                info.setId(md5ImageMap.get(info.getMd5()).getId());
            }
        }
        //更新数据
        imageBaseInfoRepository.save(imageBaseInfoCollection);
    }

    public Page<ImageBaseInfo> findPage(PageParam pageParam) {
        Page<ImageBaseInfo> page = imageBaseInfoRepository.findAll(DynamicSpecifications.bySearchFilter(pageParam.getSearchFilters()), pageParam.getPageable());
        List<ImageBaseInfo> resultList = Lists.newArrayList();
        for (ImageBaseInfo info : page.getContent()) {
            info.setImgUrl("http://" + info.getUrl() + "/" + info.getName());
            info.setImgStyle("!Thumbnail200");
            resultList.add(info);
        }
        return page;
    }


    public List<ImageBaseInfo> findList() {
        List<ImageBaseInfo> resultList = Lists.newArrayList();
        for (ImageBaseInfo info : imageBaseInfoRepository.findAll()) {
            if (info.getName().endsWith(".rar")) {
                continue;
            }
            ImageBaseInfo temp = new ImageBaseInfo();
            BeanUtils.copyProperties(info, temp);
            temp.setImgUrl("http://" + info.getUrl() + "/" + info.getName());
            temp.setImgStyle("!Thumbnail200");
            resultList.add(temp);
        }
        return resultList;
    }

}
