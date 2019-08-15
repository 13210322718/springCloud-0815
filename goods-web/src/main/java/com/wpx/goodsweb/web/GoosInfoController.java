package com.wpx.goodsweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoosInfoController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test() {
        //一目了然的URL---->将地址直接写成服务名称就可以
        String url="http://GOODS-SERVER/goods/test";
        Map<String, String> forObject = restTemplate.getForObject(url, Map.class);
        return forObject;
    }
    @RequestMapping("/test/{value}")
    @ResponseBody
    public Map<String, String> test(@PathVariable("value") String value) {
        return null;
    }
}
