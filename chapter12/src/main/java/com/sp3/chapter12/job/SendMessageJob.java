package com.sp3.chapter12.job;

import com.rabbitmq.client.Channel;
import com.sp3.chapter12.app.queue.request.CreateQueueRequest;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
public class SendMessageJob {

    private static final String JOB_NAME = "user"; //自定义队列名，不同的任务建议使用不同名称
    private static final String EXCHANGE = "amq.direct";
    private static final String QUEUE = JOB_NAME;
    private static final String ROUTE_KEY = JOB_NAME;

    @Resource
    private RabbitTemplate rabbitTemplate;

    //触发队列消息
    public void dispatch(CreateQueueRequest request) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTE_KEY, request, new CorrelationData(request.getUuid()));
    }

    //处理队列消息
    @Transactional
    @Modifying
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE), exchange = @Exchange(name = EXCHANGE), key = ROUTE_KEY), concurrency = "1-5")
    public void handle(CreateQueueRequest request, Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //TODO 业务逻辑,处理完成手动确认
            System.out.println(request);
            channel.basicAck(deliveryTag, true);//手动确认
        } catch (Exception e) {
            //异常记录
            channel.basicNack(deliveryTag, false, false);  //手动取消，不批处理，不重入队列
        }
    }
}
