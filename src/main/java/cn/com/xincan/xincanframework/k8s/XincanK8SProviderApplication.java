package cn.com.xincan.xincanframework.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XincanK8SProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XincanK8SProviderApplication.class, args);
    }

}
