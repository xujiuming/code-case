package com.ming.test.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.junit.Test;

public class TestOSS {
    private String endpoint = "http://oss.xujiuming.com";
    private String accessKeyId = "LTAIyGAcC8PSe51C";
    private String accessKeySecret = "6gwD0B9x8NYM7w1g3o7qXmoHmtrku5";
    private String bucket = "imgming";


    @Test
    public void testOss() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ObjectListing ossObjectListing = ossClient.listObjects(bucket);
        for (OSSObjectSummary summary : ossObjectListing.getObjectSummaries()) {
            System.out.println(summary.getKey());
        }
    }
}
