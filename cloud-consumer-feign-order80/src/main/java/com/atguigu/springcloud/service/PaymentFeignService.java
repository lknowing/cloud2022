package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 声明远程调用的接口：Feign服务接口
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //指明调用哪个远程服务
public interface PaymentFeignService {

    //声明调用的服务接口方法   与远程controller中方法声明保持一致

    @GetMapping("/payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);
}
