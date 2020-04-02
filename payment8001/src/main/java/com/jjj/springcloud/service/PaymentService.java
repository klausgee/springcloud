package com.jjj.springcloud.service;

import com.jjj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getById(@Param("id") Integer id);
}
