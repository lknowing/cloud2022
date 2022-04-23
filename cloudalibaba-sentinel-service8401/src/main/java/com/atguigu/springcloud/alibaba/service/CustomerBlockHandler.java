package com.atguigu.springcloud.alibaba.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

public class CustomerBlockHandler {

    /**
     *  独立降级处理类中的降级方法必须是static修饰的。
     */
    public static CommonResult<Payment> handleException(BlockException exception){
        return new CommonResult<Payment>(432,exception.getClass().getCanonicalName()+"\t 服务不可用432");
    }

    public static CommonResult<Payment> fallbackException(@PathVariable("id") Long id,BlockException exception){ //配置违规异常类型
        return new CommonResult<Payment>(444,exception.getClass().getCanonicalName()+"\t 服务不可用444");
    }

    public static CommonResult<Payment> fallbackException2(@PathVariable("id") Long id, Throwable exception){ //程序异常 异常类型
        return new CommonResult<Payment>(456,exception.getClass().getCanonicalName()+"\t 服务不可用456");
    }
}
