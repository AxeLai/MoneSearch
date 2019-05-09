package com.mone.search.config;

import com.mone.search.receiver.GoodsReceiver;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2813:54
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    GoodsReceiver receiver(CountDownLatch latch) {
        return new GoodsReceiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    /**
     * 创建连接工厂
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("goods"));
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(GoodsReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMassage");
    }

}
