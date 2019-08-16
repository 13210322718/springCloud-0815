package com.wpx.goodsserver.dao;

import com.wpx.goodsserver.domain.GoodsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoodsInfoDao extends JpaRepository<GoodsInfo, String> {
    @Query(nativeQuery = true, value = "select * from GoodsInfo")
    Page<GoodsInfo> findAll(Pageable pageable);
}
