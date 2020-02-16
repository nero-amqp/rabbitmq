package com.nero.www.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Map;

@Configuration
public class RabbitmqSubscribeService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqSubscribeService.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queue1"),
            exchange = @Exchange(value = "exchange1", type = "fanout")
    ))
    @RabbitHandler
    public void onmessage(@Payload String message, @Headers Map<String, Object> headers, Channel channel){

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        logger.info("queue1 message coming: {}, deliveryTag {} ", message, deliveryTag);

        try {
            channel.basicAck(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queue2"),
            exchange = @Exchange(value = "exchange1", type = "fanout")
    ))
    @RabbitHandler
    public void onmessage2(@Payload String message, @Headers Map<String, Object> headers, Channel channel){

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        logger.info("queue2 message coming: {}, deliveryTag {} ", message, deliveryTag);

        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
