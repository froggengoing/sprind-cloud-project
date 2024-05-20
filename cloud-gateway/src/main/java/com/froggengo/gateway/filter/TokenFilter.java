package com.froggengo.gateway.filter;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.froggengo.gateway.config.GatewayUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author fly
 * @create 2024-05-18-1:32
 **/
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * @value 无法处理复杂的类型
     */
    //@Value("${sys.url.black-list}")
    //List<String> list = new ArrayList<>();

    @Autowired
    GatewayUrlProperties gatewayProperties;

    //{
    //    list.add("/user/hello1");
    //}

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("自定义拦截器");
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        for (String pattern : gatewayProperties.getBlackList()) {
            if (pathMatcher.match(pattern, path.value())) {
                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("status", HttpStatus.FORBIDDEN.value());
                jsonObject.addProperty("msg", "Forbidden");
                jsonObject.addProperty("data", "null");
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                //response.setStatusCode(HttpStatus.FORBIDDEN);
                response.setStatusCode(HttpStatus.OK);
                Mono<DataBuffer> buffer = Mono.just(response.bufferFactory().wrap(jsonObject.toString().getBytes()));
                return response.writeWith(buffer);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
