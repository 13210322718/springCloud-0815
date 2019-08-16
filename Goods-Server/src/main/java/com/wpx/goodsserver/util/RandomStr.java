package com.wpx.goodsserver.util;

import java.util.UUID;

public class RandomStr {

    public static String getRandomStr() {
        Integer orderId = UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId + "";
    }

}
