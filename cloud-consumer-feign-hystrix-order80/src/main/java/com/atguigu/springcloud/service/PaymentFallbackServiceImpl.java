package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 降级处理类：只针对于远程调用方法出问题进行降级处理。
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "远程调用【CLOUD-HYSTRIX-PAYMENT-SERVICE】【paymentInfo_OK】出问题了.降级处理";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "远程调用【CLOUD-HYSTRIX-PAYMENT-SERVICE】【paymentInfo_Timeout】出问题了.降级处理";
    }
}
