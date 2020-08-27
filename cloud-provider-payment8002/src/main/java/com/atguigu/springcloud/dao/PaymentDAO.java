package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.atguigu.springcloud.entities.Payment;

@Mapper
@Component
public interface PaymentDAO
{
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
