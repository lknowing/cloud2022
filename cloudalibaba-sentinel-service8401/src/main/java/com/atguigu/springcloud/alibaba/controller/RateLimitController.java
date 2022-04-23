package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController{

    /**
     *  1.按照uri配置规则，走默认降级处理消息：Blocked by Sentinel (flow limiting) 不友好。
     *  2.按照资源名称配置规则，走blockHandler降级方法。返回自定义消息 友好。
     *  3.代码膨胀和耦合问题？
     *      解决：定义独立降级处理类，方法要求必须static修饰的。必须public,返回类型必须一致，参数必须一致，最后多一个BlockException参数
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource123",
            blockHandler = "handleException",blockHandlerClass = CustomerBlockHandler.class
    )
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    /*public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }*/


    /**
     * blockHandler 用于处理配置违规等进行降级处理
     * fallback 用于处理自身代码异常等进行降级处理
     * fallback和blockHandler :两种情况同时存在，配置违规处理优先。
     * exceptionsToIgnore：抛指定异常，不执行fallback降级处理。
     *
     */
    @RequestMapping("/fallback/{id}")
    @SentinelResource(value = "byFallbackName",
            blockHandler = "fallbackException",blockHandlerClass = CustomerBlockHandler.class,
            fallback = "fallbackException2",fallbackClass = CustomerBlockHandler.class,
            exceptionsToIgnore=IllegalArgumentException.class
    )
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }

        if (id==-1) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        CommonResult<Payment> result = new CommonResult<Payment>(200,"数据已经获取",new Payment(id,"test"+1));
        return result;
    }









}
