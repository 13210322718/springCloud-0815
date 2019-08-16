package com.wpx.goodsserver.server.impl;

import com.wpx.goodsserver.dao.CommentsInfoDao;
import com.wpx.goodsserver.dao.GoodsInfoDao;
import com.wpx.goodsserver.domain.CommentsInfo;
import com.wpx.goodsserver.domain.GoodsInfo;
import com.wpx.goodsserver.server.GoodsInfoServer;
import com.wpx.goodsserver.util.PageRest;
import com.wpx.goodsserver.util.RandomStr;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GoodsInfoServerImpl implements GoodsInfoServer {
    @Autowired
    GoodsInfoDao goodsInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageRest<GoodsInfo> getAll(Integer start, Integer end) throws Exception {
        //生成一个页面信息对象
        PageRest<GoodsInfo> goodsInfoPageRest = new PageRest<>();
        //设置序列化
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        List<GoodsInfo> goodsInfo1 = null;
        Long goodsInfoCount = 0L;
        try {
            //分页在redis中获取所有的商品
            goodsInfo1 = this.redisTemplate.opsForList().range("goodsInfo", start * end, start * end + end - 1);
            //获取总数量
            goodsInfoCount = this.redisTemplate.opsForList().size("goodsInfo");
        } catch (Exception e) {
            e.getStackTrace();
        }
        //如果在Redis没有获取到
        if (goodsInfo1 == null || goodsInfo1.size() < 1) {
            //配置分页配置
            Pageable pageable = PageRequest.of(start, end);
            //进行分页配置
            Page<GoodsInfo> all = goodsInfoDao.findAll(pageable);
            List<GoodsInfo> goodsInfos = new ArrayList<>();
            //获取分页内的每一个属性
            for (GoodsInfo goodsInfo : all) {
                goodsInfos.add(goodsInfo);
            }
            goodsInfoPageRest.setData(goodsInfos);
            goodsInfoPageRest.setCount(all.getTotalElements());
        }
        //如果已经在Redis中获取到商品信息
        else {
            goodsInfoPageRest.setData(goodsInfo1);
            goodsInfoPageRest.setCount(goodsInfoCount);
        }
        return goodsInfoPageRest;
    }

    @Override
    public GoodsInfo getOneById(String no) throws Exception {
        //设置序列化
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //分页获取所有的商品
        List<GoodsInfo> goodsInfo1 = this.redisTemplate.opsForList().range("goodsInfo", 0, -1);
        //如果在Redis中没有数据
        if (goodsInfo1.size() < 1 || goodsInfo1 == null) {
            Optional<GoodsInfo> byId = goodsInfoDao.findById(no);
            if (byId.isPresent()) {
                GoodsInfo goodsInfo = byId.get();
                return goodsInfo;
            } else {
                return null;
            }
        }
        //List中有数据
        else {
            GoodsInfo result = null;
            //遍历redis列表，在里边查找到指定的GoodsInfo
            for (GoodsInfo goodsInfo : goodsInfo1) {
                if (no.equals(goodsInfo.getGoodsNo())) {
                    result = goodsInfo;
                    break;
                }
            }
            //如果没有找到
            if (result == null) {
                //在数据库里查找
                Optional<GoodsInfo> byId = goodsInfoDao.findById(no);
                if (byId.isPresent()) {
                    GoodsInfo goodsInfo = byId.get();
                    return goodsInfo;
                } else {
                    return null;
                }
            }
            //如果找到了
            else {
                CommentsInfoServerImpl commentsInfoServer = new CommentsInfoServerImpl();
                //找到该商品的所有评论
                List<CommentsInfo> allByGoodsNo = commentsInfoServer.findAllByGoodsNo(no);
                for (CommentsInfo commentsInfo : allByGoodsNo) {
                    result.addComments(commentsInfo);
                }
                return result;
            }
        }
    }

    @Override
    public boolean addGoods(GoodsInfo goodsInfo) {
        //设置商品编号
        goodsInfo.setGoodsNo(RandomStr.getRandomStr());
        goodsInfoDao.save(goodsInfo);
        //设置序列化
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //将该商品信息序列化放入列表中
        redisTemplate.opsForList().leftPush("goodsInfo", goodsInfo);
        return true;
    }
}
