package com.sp3.chapter12.job;

import com.rabbitmq.client.Channel;
import com.sp3.chapter12.app.queue.request.CreateQueueRequest;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;

import java.io.IOException;
import java.util.Date;

@Configuration
public class DelaySendMessageJob {

    private static final String JOB_NAME = "delay-user";
    private static final String EXCHANGE = "amq.direct";
    private static final String QUEUE = JOB_NAME + ".queue";
    private static final String ROUTING_KEY = JOB_NAME + ".key";
    private static final String DELAY_QUEUE = JOB_NAME + ".delay.queue";
    private static final String DELAY_ROUTING_KEY = JOB_NAME + ".delay.key";

    @Resource
    private RabbitTemplate rabbitTemplate;

    //声明死信交换机
    @Bean
    public Exchange myExchange() {
        return ExchangeBuilder.directExchange("amq.direct").durable(true).build();
    }

    //声明死信队列，并设置延迟交换机和延迟路由
    @Bean
    public Queue myQueue() {
        return QueueBuilder.durable(QUEUE).deadLetterExchange("amq.direct").deadLetterRoutingKey(DELAY_ROUTING_KEY).build();
    }

    //声明死信交换机和死信队列的绑定，以及死信路由
    @Bean
    public Binding myBinding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with(ROUTING_KEY).noargs();
    }

    //声明延迟交换机
    @Bean
    public org.springframework.amqp.core.Exchange delayExchange() {
        return ExchangeBuilder.directExchange("amq.direct").durable(true).build();
    }

    //声明延迟队列
    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }

    //声明延迟交换机和延迟队列绑定关系，以及延迟路由
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }

    public void dispatch(CreateQueueRequest request,int delayTime) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, request, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTime*1000));
            return message;
        });
        System.out.println("发送消息: " + "延迟: " + 30000 + "ms, 当前时间: " + new Date());
    }

    //监听死信队列，获取消息
    @Transactional
    @Modifying
    @RabbitListener(queues = DELAY_QUEUE)
    public void execute(CreateQueueRequest request, Message message, Channel channel) throws IOException {
        System.out.println("接收消息: " + " 当前时间： " + new Date());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //手动确认
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //手动取消，不批处理，不重入队列
            channel.basicNack(deliveryTag, false, false);
        }
    }
}
