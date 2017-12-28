package org.cx.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义实现 {@link com.netflix.loadbalancer.IRule}
 *
 * @author grass
 * @date 2017/12/4
 */
public class FirstServerRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = getLoadBalancer();

        // 返回三个配置 Server，即：
        // person-service.ribbon.listOfServers = http://localhost:9999,http://localhost:9999,http://localhost:9999
        List<Server> allServers = loadBalancer.getAllServers();
        return allServers.get(0);
    }

}
