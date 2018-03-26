package com.ming.test.base;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by xianyu on 17-9-24.
 */
public class UpdateImageName {

    private String imgUrl = "/home/xianyu/img";

    @Test
    public void update() throws IOException {
        File file = new File(imgUrl);
        File[] files = file.listFiles();
        for (File f : files) {
            String name = System.nanoTime() + "ming";
            String filename = f.getAbsolutePath();
            String fileType = new String();
            if (filename.indexOf(".") >= 0) {
                //filename   =   filename.substring(0,filename.lastIndexOf("."));
                fileType = filename.substring(filename.lastIndexOf("."), filename.length());
            }
            f.renameTo(new File(imgUrl + "/" + name + fileType));   //改名
            System.out.println(f);
        }

    }

}
