package java_springboot.rabbitmq_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    //Queue for Json Data
    @Value("${rabbitmq.json.queue.name}")
    private String JsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    //Instance Variable for routing key
    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    //create routingkey instance for the Json queue
    @Value("${rabbitmq.json.routingkey}")
    private String routingKey;

    //Spring bean for rabbitMQ Json Queue
    @Bean
    public Queue JsonQueue()
    {
        return new Queue(JsonQueue);
    }

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

    //Binding class for Json Queue
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(JsonQueue())
                .to(exchange())
                .with(routingKey);
    }

    //For Json we need to create a rabbit template to support sending json message-use message converter

    //Helper Converter class used for message to be sent to the rabbit template
    @Bean
    public MessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    //Method for rabbit template
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate((connectionFactory));
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin - These are infrastructure beans required for springboot to work with rabbitmq broker but springboot
    //autoconfiguration will automatically configure this for us
}
