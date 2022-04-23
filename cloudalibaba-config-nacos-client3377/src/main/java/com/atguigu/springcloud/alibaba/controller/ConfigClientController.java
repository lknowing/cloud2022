package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态更新配置中心拉取的配置
public class ConfigClientController {

    @Value("${config.info}")
    String info; //从配置中心拉取配置信息，dataid命名是有规则的。

    @GetMapping("/config/info")
    public String info(){
        System.out.println("info = " + info);
        return info;
    }
}
