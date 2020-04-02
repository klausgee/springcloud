package com.jjj.springcloud.controller;

import com.jjj.springcloud.entities.CommonResult;
import com.jjj.springcloud.entities.Payment;
import com.jjj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value ="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果:"+result);
        if(result>0){
            return new CommonResult(200,"成功,"+serverPort,payment);
        }else{
            return new CommonResult(444,"插入数据失败,"+serverPort,null);
        }
    }

    @GetMapping(value ="/payment/get/{id}")
    public CommonResult getById(@PathVariable Integer id){
        Payment payment = paymentService.getById(id);
        log.info("*****查询结果嘻嘻嘻:"+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功,"+serverPort,payment);
        }else{
            return new CommonResult(444,"查询数据失败，查询id"+id+","+serverPort,null);
        }
    }
}
