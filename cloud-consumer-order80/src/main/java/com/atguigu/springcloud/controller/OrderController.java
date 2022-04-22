package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.smartcardio.CommandAPDU;

@RestController
public class OrderController {

    //public static final String PATH = "http://localhost:8001";
    public static final String PATH = "http://CLOUD-PAYMENT-SERVICE"; //通过服务名称到注册中心拉取服务

    @Autowired
    RestTemplate restTemplate;
    
    //浏览器 ->  消费者  -> 提供者
    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        CommonResult result = restTemplate.postForObject(PATH+"/payment/create", payment, CommonResult.class);
        System.out.println("result = " + result);
        return result;
    }

    @GetMapping("/consumer/payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(PATH+"/payment/getById/"+id, CommonResult.class);
        System.out.println("result = " + result);
        return result;
    }


    //==> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PATH+"/payment/zipkin/", String.class);
        return result;
    }

}
