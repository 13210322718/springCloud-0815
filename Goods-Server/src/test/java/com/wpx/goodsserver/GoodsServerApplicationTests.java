package com.wpx.goodsserver;

import com.wpx.goodsserver.dao.CommentsInfoDao;
import com.wpx.goodsserver.domain.CommentsInfo;
import com.wpx.goodsserver.domain.GoodsInfo;
import com.wpx.goodsserver.server.CommentsInfoServer;
import com.wpx.goodsserver.server.GoodsInfoServer;
import com.wpx.goodsserver.util.PageRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServerApplicationTests {
    @Autowired
    GoodsInfoServer goodsInfoServer;
    @Autowired
    CommentsInfoServer commentsInfoServer;
    @Test
    public void test(){

    }

    @Test
    public void addGoods() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setBrandNo(2);
        goodsInfo.setGoodsName("小米9S");
        goodsInfo.setGoodsDesc("这是第一个的测试商品");
        goodsInfo.setGoodsPiceture("/picture/aaa.jpg");
        goodsInfo.setGoodsPrice(10110.8);
        goodsInfoServer.addGoods(goodsInfo);
    }

    @Test
    public void addComm() throws Exception {
        CommentsInfo commentsInfo = new CommentsInfo();
        GoodsInfo oneById = goodsInfoServer.getOneById("1593913959");
        // commentsInfo.setGoodsInfo(oneById);
        commentsInfo.setUserNo("888888888");
        commentsInfo.setCommentsContext("这件商品不太适合我");
        commentsInfoServer.addComments(commentsInfo);
    }

    @Test
    @Transactional
    public void findGoodsAll() throws Exception{
        PageRest<GoodsInfo> all = goodsInfoServer.getAll(0, 10);
        for (int i = 0; i <all.getData().size() ; i++) {
            System.out.println(all.getData().get(i).getGoodsName());
            System.out.println(all.getData().get(i).getCommentsInfos());
        }
    }

    @Test
    @Transactional
    public void findGoods() throws Exception {
        GoodsInfo oneById = goodsInfoServer.getOneById("1593913959");
        System.out.println(oneById.getGoodsNo() + "-->" + oneById.getGoodsName());
		System.out.println();
        Set<CommentsInfo> commentsInfos = oneById.getCommentsInfos();
		System.out.println(commentsInfos);
    }
}
