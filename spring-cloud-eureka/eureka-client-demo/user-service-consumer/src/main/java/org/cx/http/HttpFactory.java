package org.cx.http;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author grass
 * @date 2017/11/14
 */
public class HttpFactory {
    public static void main(String[] args) {
        /**
         * 可以使用不同的httpclient实现,实现了ClientHttpRequest接口
         * HttpComponentsClientHttpRequestFactory
         * OkHttpClientHttpRequestFactory
         *
         */
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String str = restTemplate.getForObject("http://www.baidu.com", String.class);

        System.out.println(str);
    }
}
