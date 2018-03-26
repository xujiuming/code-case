package com.ming.controller.api;

import com.ming.api.oss.OssUtils;
import com.ming.core.utils.AliyunAPIUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 外部 接口 ip相关控制器
 *
 * @author ming
 * @date 2017-06-24
 */
@RestController
@RequestMapping(value = "api")
public class APIController {


    @GetMapping(value = "/ip/get")
    public String getIp(String ip) throws Exception {
        return AliyunAPIUtils.getIpInfo(ip);
    }

    @GetMapping(value = "/ip/config/get")
    public String getIConfig() {
        return AliyunAPIUtils.ipConfigToJsonString();
    }

    @GetMapping(value = "/weather/get")
    public String getWeather(String area) throws Exception {
        return AliyunAPIUtils.getWeather(area);
    }

    @GetMapping(value = "/weather/config/get")
    public String getWeatherConfig() {
        return AliyunAPIUtils.weatherConfigToJsonString();
    }


    @GetMapping(value = "oss/push")
    @ResponseBody
    public String pushOssImage() {
        Long now = System.currentTimeMillis();
        OssUtils.pushOssFuture();
        //先不使用异步
        //OssUtils.pushOssImage();
        return "刷新成功" + (System.currentTimeMillis() - now) + "ms";
    }
}
