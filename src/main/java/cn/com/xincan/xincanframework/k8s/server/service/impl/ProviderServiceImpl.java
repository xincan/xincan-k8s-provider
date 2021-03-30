package cn.com.xincan.xincanframework.k8s.server.service.impl;

import cn.com.xincan.xincanframework.k8s.server.entity.Provider;
import cn.com.xincan.xincanframework.k8s.server.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author JiangXincan
 * @version 0.0.1
 * @program xincan-k8s-provider
 * @description 服务提供者业务实现层
 * @create 2021/3/9 20:40
 * <author>                <time>                  <version>                   <description>
 * Jiangxincan         2021/3/9 20:40             0.0.1                        服务提供者业务实现层
 */
@Slf4j
@Service
public class ProviderServiceImpl implements ProviderService {

    @Value("${spring.profiles.active}")
    private static String active;

    @Override
    public Provider info() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return Provider.builder()
                    .hostName(addr.getHostName())
                    .hostAddress(addr.getHostAddress())
                    .active(active)
                    .currentTime(LocalDateTime.now())
                    .build();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

}
