package com.data.itsv.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

   /* // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(User user) {
        log.info("【receiverDirectQueue监听到消息】" + user.toString());
    }
    // queues是指要监听的队列的名字
    *//*@RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(User user) {
        log.info("【receiveTopic1监听到消息】" + user.toString());
    }*//*
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(User user, Channel channel, Message message) {
        log.info("【receiveTopic2监听到消息】" + user.toString());
      *//*  try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
    }*/
}