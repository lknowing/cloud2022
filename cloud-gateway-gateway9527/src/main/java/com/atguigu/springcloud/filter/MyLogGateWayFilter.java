package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Component //声明bean对象，过滤器就可以生效。无需任何配置。
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //请求目前前执行
        System.out.println("new Date = " + new Date());
        // url?username=zhangsan&username=lisi
        //List<String> list = exchange.getRequest().getQueryParams().get("username");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        System.out.println("username = " + username);
        if(StringUtils.isEmpty(username)){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            Mono<Void> mono = exchange.getResponse().setComplete();//拒绝访问
            return mono;
        }
        Mono<Void> mono = chain.filter(exchange);//放行


        //请求目标执行后执行

        return mono;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
