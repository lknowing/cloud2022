package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String port;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if(i==1){
            return new CommonResult(200,"添加成功");
        }else{
            return new CommonResult(444,"添加失败");
        }
    }

    @GetMapping("/payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        try {
            System.out.println("port = " + port);
            //TimeUnit.SECONDS.sleep(3);
        }catch (Exception e) {
            e.printStackTrace();
        } //单位秒
        if(payment!=null){
            return new CommonResult(200,"查询数据成功 port="+port,payment);
        }else{
            return new CommonResult(444,"查询数据不存在");
        }
    }
}
