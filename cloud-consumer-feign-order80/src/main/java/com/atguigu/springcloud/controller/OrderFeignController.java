package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    PaymentFeignService paymentFeignService; //注入代理对象

    @GetMapping("/consumer/payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        CommonResult commonResult = paymentFeignService.getById(id); //通过OpenFeign接口远程调用。面向接口调用。
        System.out.println("commonResult = " + commonResult);
        return commonResult;
    }
}
