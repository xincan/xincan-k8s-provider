package cn.com.xincan.xincanframework.k8s.server.controller;

import cn.com.xincan.xincanframework.k8s.server.entity.Provider;
import cn.com.xincan.xincanframework.k8s.server.service.ProviderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author JiangXincan
 * @version 0.0.1
 * @program xincan-k8s-provider
 * @description 定义服务器提供者控制层
 * @create 2021/3/4 17:22
 * <author>                <time>                  <version>                   <description>
 * Jiangxincan         2021/3/4 17:22             0.0.1                        定义服务器提供者控制层
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    private  final DiscoveryClient discoveryClient;
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService, DiscoveryClient discoveryClient) {
        this.providerService = providerService;
        this.discoveryClient = discoveryClient;
    }

    /**
     * 探针检查响应类
     * @return String
     */
//    @GetMapping("/health")
//    public String health() {
//        return "health";
//    }


    /**
     * 返回发现的所有服务
     * @return String
     */
    @GetMapping("/services")
    public JSONObject services() {
        JSONObject service = new JSONObject();
        service.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        service.put("serviceList", this.discoveryClient.getServices());
        return service;
    }

    @GetMapping("/instance")
    public Object getInstance(@RequestParam("name") String name){
        return discoveryClient.getInstances(name);
    }

    @GetMapping("/info")
    public Provider info() {
        return this.providerService.info();
    }
}
