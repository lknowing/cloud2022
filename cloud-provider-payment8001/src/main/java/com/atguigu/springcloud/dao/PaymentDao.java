package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
public interface PaymentDao{
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
