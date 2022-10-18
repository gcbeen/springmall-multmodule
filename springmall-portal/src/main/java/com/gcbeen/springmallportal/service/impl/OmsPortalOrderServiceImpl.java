package com.gcbeen.springmallportal.service.impl;

import com.gcbeen.springmallportal.dto.OrderParam;
import com.gcbeen.springmallcommon.util.Result;
import com.gcbeen.springmallportal.component.CancelOrderSender;
import com.gcbeen.springmallportal.service.IOmsPortalOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理 Service
 */
@Service
public class OmsPortalOrderServiceImpl implements IOmsPortalOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public Result<Object> generateOrder(OrderParam orderParam) {
        // todo 执行一系列下单操作
        LOGGER.info("process generateOrder");
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单
        sendDelayMessageCancelOrder(11L);
        return Result.success(null, "下单成功");
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 获取订单超时时间
        long delayTimes = 30 * 1000; // 30秒
        // 发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        // todo 执行一系列取消订单操作
        LOGGER.info("process cancelOrder orderId : {}", orderId);
    }
    
}
