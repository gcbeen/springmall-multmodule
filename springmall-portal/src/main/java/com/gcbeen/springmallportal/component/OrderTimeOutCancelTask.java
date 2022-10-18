package com.gcbeen.springmallportal.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);
    
    /**
     * cron 表达式：Seconds Minutes Hours DayofMonth Month DayOfWeek [Year]
     * 每 10 分钟扫描一次，扫描 超时订单 没有支付则取消订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        LOGGER.info("订单取消，并根据sku编号释放锁定库存");
    }
}
