package com.wpx.goodsserver.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {


    @RequestMapping("/test")
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "aaaa");
        map.put("2", "bbbb");
        map.put("3", "cccc");
        map.put("4", "dddddd");
        map.put("5", "eeeee");
        map.put("6", "fff");
        return map;
    }

    @RequestMapping("/test/{value}")
    public Map<String, String> test(@PathVariable("value") String value) {
        Map<String, String> map = new HashMap<>();
        map.put("msg", value);
        return map;
    }

}
