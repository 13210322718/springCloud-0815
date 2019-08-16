package com.wpx.goodsserver.dao;

import com.wpx.goodsserver.domain.CommentsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsInfoDao extends JpaRepository<CommentsInfo, Integer> {

    List<CommentsInfo> findCommentsInfoByGoodsNo(String no);

}
