package com.atguigu.springcloud.service.impl;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.PaymentDAO;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDAO paymentDAO;

    @Override
    public int create(Payment payment)
    {
        return paymentDAO.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id)
    {
        return paymentDAO.getPaymentById(id);
    }
}
