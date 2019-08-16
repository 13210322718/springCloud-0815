package com.wpx.goodsweb.web;

import com.wpx.goodsweb.domain.GoodsInfo;
import com.wpx.goodsweb.util.PageRest;
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
        String url = "http://GOODS-SERVER/goods/test";
        Map<String, String> forObject = restTemplate.getForObject(url, Map.class);
        return forObject;
    }

    //获取所有的商品列表
    @RequestMapping("/all/{start}")
    @ResponseBody
    public PageRest<GoodsInfo> getAll(@PathVariable(name = "start") Integer start) {
        String url = "http://GOODS-SERVER/goods/all/" + start;
        PageRest<GoodsInfo> forObject = restTemplate.getForObject(url, PageRest.class);
        return forObject;
    }

    //获取详情
    @RequestMapping("/one/{no}")
    @ResponseBody
    public PageRest<GoodsInfo> findOne(@PathVariable(name = "no") String no) {
        String url = "http://GOODS-SERVER/goods/one/" + no;
        PageRest forObject = restTemplate.getForObject(url, PageRest.class);
        return forObject;
    }

    @RequestMapping("/add/{values}")
    @ResponseBody
    public PageRest<String> addGoods(@PathVariable(name = "values") String values) {
        String url = "http://GOODS-SERVER/goods/add/" + values;
        PageRest forObject = restTemplate.getForObject(url, PageRest.class);
        return forObject;
    }

}
