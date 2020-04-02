package com.jjj.springcloud.controller;

import com.jjj.springcloud.entities.CommonResult;
import com.jjj.springcloud.entities.Payment;
import com.jjj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")   //获取到 yml中配置的serverport
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

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
    @GetMapping("/payment/discovery")
    public Object discover(){
        //查询到所有服务名
        List<String> service = discoveryClient.getServices();
        for(String a : service){
            log.info("*****element" +a);
        }
        //查询到某个服务实例的相关信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance i:instances){
            log.info(i.getServiceId()+"\t"+i.getHost()+"\t"+i.getPort()+"\t"+i.getUri());
        }
        return this.discoveryClient;
    }
}
