package com.atguigu.springcloud.service.impl;

import java.util.UUID;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.atguigu.springcloud.service.IMessageProvider;
import javax.annotation.Resource;

/**
 *
 * 这里没有 @Service, 注意！！
 * 因为 @EnableBinding 里面引用了 BindingBeansRegistrar ，它会自动注册
 */
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send()
    {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return null;
    }
}