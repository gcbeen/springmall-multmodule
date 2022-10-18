package com.gcbeen.springmallportal.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcbeen.springmallportal.service.IOmsPortalOrderService;

/**
 * 取消订单消息处理者
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private IOmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId) {
        LOGGER.info("receive delay message orderId : {}", orderId);
        portalOrderService.cancelOrder(orderId);
    }


}
