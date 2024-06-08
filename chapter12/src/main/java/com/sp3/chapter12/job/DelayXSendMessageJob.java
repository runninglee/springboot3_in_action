package com.sp3.chapter12.job;

import com.rabbitmq.client.Channel;
import com.sp3.chapter12.app.queue.request.CreateQueueRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;

@Configuration
@Slf4j
public class DelayXSendMessageJob {

    //第一步: 声明任务名称，标识要唯一
    private static final String JOB_NAME = "user";

    //第二步:首次手动创建延迟交换机 delay.exchange
    private static final String QUEUE = "x." + JOB_NAME;
    private static final String ROUTE_KEY = "x.route." + JOB_NAME;
    private static final String EXCHANGE = "x.delay";

    @Resource
    private RabbitTemplate rabbitTemplate;

    //第三步: 传任务的ID和延迟时间,默认为秒
    public void dispatch(CreateQueueRequest request, int delayTime) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTE_KEY, request, message -> {
            message.getMessageProperties().setDelayLong((long) delayTime * 1000);
            return message;
        });
    }

    //第四步: 绑定队列信息到延迟交换机上
    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE), exchange = @Exchange(name = EXCHANGE, type = "x-delayed-message", arguments = {@Argument(name = "x-delayed-type", value = "direct")}, delayed = "true"), key = ROUTE_KEY))
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
