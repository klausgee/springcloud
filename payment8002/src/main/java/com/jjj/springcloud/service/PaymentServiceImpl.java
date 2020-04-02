package com.jjj.springcloud.service;

import com.jjj.springcloud.dao.PaymentDao;
import com.jjj.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(Integer id) {
        return paymentDao.getById(id);
    }
}
