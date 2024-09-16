package com.huangsikai.eventresspring.mq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

import static com.huangsikai.eventresspring.mq.Config.DIRECT_EXCHANGE;

/**
 * Routing 路由模型（交换机类型：direct）
 * 消息生成者
 * @author qzz
 */
@Component
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param routingKey
     * @param msg
     */
    public void send (String routingKey,String msg){
        log.error("收到消息"+msg);
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,routingKey,msg);
    }
}
