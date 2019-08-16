package com.wpx.goodsserver.server.impl;

import com.wpx.goodsserver.dao.CommentsInfoDao;
import com.wpx.goodsserver.domain.CommentsInfo;
import com.wpx.goodsserver.server.CommentsInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentsInfoServerImpl implements CommentsInfoServer {
    @Autowired
    CommentsInfoDao commentsInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<CommentsInfo> findAllByGoodsNo(String no) throws Exception {
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //在缓存中获取某商品的所有评论
        List<CommentsInfo> range = this.redisTemplate.opsForList().range(no, 0, -1);
        if (range == null || range.size() < 1) {
            return commentsInfoDao.findCommentsInfoByGoodsNo(no);
        } else {
            return range;
        }
    }

    @Override
    public void addComments(CommentsInfo commentsInfo) throws Exception {
        commentsInfo.setBornDate(new Date());
        commentsInfoDao.save(commentsInfo);
        //将评论存入到缓存中去
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForList().leftPush(commentsInfo.getGoodsNo(), commentsInfo);
    }
}
