package com.gcbeen.springmallportal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcbeen.springmallportal.dto.QueueEnum;

// 交换机及队列说明
// mall.order.direct（取消订单消息队列所绑定的交换机）
// 绑定的队列为mall.order.cancel，
// 一旦有消息以mall.order.cancel为路由键发过来，会发送到此队列。

// mall.order.direct.ttl（订单延迟消息队列所绑定的交换机）:
// 绑定的队列为mall.order.cancel.ttl，
// 一旦有消息以mall.order.cancel.ttl为路由键发送过来，会转发到此队列，并在此队列保存一定时间，
// 等到超时后会自动将消息发送到mall.order.cancel（取消订单消息消费队列）。

/**
 * 消息队列配置
 */
@Configuration
public class RabbitMqConfig {


    /**
     * 将订单队列绑定到交换机
     * @param orderDirect
     * @param orderQueue
     * @return
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
            .bind(orderQueue)
            .to(orderDirect)
            .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }


    /**
     * 订单消息 实际消息队列所绑定的交换机
     * @return
     */
    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
            .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
            .durable(true)
            .build();
    }

    /**
     * 订单消息 实际消息队列
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }






    /**
     * 将订单延迟队列绑定到交换机
     * @param orderDirect
     * @param orderQueue
     * @return
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue) {
        return BindingBuilder
            .bind(orderTtlQueue)
            .to(orderTtlDirect)
            .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }


    /**
     * 订单消息 延迟队列所绑定的交换机
     * @return
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return ExchangeBuilder
            .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
            .durable(true)
            .build();
    }
    /**
     * 订单 延迟队列 (死信队列)
     * @return
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
            .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
            .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
            .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
            .build();
    }

}
