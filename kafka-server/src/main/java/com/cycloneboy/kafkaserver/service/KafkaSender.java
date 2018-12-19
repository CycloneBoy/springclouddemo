package com.cycloneboy.kafkaserver.service;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-09-24 19:51
 **/
@Component
public class KafkaSender<T> {

    private Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(T obj){
        String jsonObj = JSON.toJSONString(obj);
        logger.info(" ----- message = {}" , jsonObj);

        // send message
        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send("kafka.tut",jsonObj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Produce:  The message failed to be send: {}",throwable.getMessage() );
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                // Todo 业务处理
                logger.info("Produce:  The message was send successful");
                logger.info("Produce: -+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_++__+ reult : {}" , stringObjectSendResult.toString());
            }
        });
    }
}
