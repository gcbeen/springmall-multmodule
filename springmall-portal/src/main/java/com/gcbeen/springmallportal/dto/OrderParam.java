package com.gcbeen.springmallportal.dto;

import lombok.Data;

/**
 * 生成订单时传入的参数
 */
@Data
public class OrderParam {
    //收货地址 ID
    private Long memberReceiveAddressId;
    // 优惠券 ID
    private Long couponId;
    // 使用的积分数
    private Integer useIntegration;
    // 支付方式
    private Integer payType;
}
