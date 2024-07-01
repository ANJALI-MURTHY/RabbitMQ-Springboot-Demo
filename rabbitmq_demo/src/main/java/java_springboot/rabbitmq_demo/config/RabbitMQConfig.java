package java_springboot.rabbitmq_demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    //Instance Variable for routing key
    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    //Spring bean for RabbitMQ Queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //Spring bean for exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Builder class to Create binding between exchange and queue using routing key
    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing_key);
    }

    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin - These are infrastructure beans required for springboot to work with rabbitmq broker but springboot
    //autoconfiguration will automatically configure this for us
}
