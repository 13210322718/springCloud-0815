package com.wpx.goodsserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpx.goodsserver.dao.CommentsInfoDao;
import com.wpx.goodsserver.domain.CommentsInfo;
import com.wpx.goodsserver.server.CommentsInfoServer;
import com.wpx.goodsserver.util.PageRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/comment")
public class CommentsInfoController {
    @Autowired
    CommentsInfoServer commentsInfoServer;

    @RequestMapping("/add/{values}")
    public PageRest<String> add(@PathVariable(name = "values") String values) {
        PageRest<String> pageRest = new PageRest();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CommentsInfo commentsInfo = objectMapper.readValue(values, CommentsInfo.class);
            commentsInfoServer.addComments(commentsInfo);
            pageRest.setCode(200);
        } catch (IOException e) {
            e.printStackTrace();
            pageRest.setCode(400);
        } finally {
            return pageRest;
        }
    }
    @RequestMapping("/One/{no}")
    public PageRest<CommentsInfo> oneGoodsComm(@PathVariable(name = "no") String no) {
        PageRest pageRest = new PageRest();
        try {
            List<CommentsInfo> allByGoodsNo = commentsInfoServer.findAllByGoodsNo(no);
            pageRest.setData(allByGoodsNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return pageRest;
        }

    }

}
