package com.wpx.goodsweb.web;

import com.wpx.goodsweb.util.PageRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/comment")
public class CommentsInfoController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/add/{values}")
    public PageRest<String> add(@PathVariable(name = "values") String values) {
        String url = "http://GOODS-SERVER/comment/add/" + values;
        PageRest forObject = restTemplate.getForObject(url, PageRest.class);
        return forObject;
    }
}
