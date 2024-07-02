package java_springboot.rabbitmq_demo.controller;

import java_springboot.rabbitmq_demo.dto.User;
import java_springboot.rabbitmq_demo.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MessageJsonController {

    //Inject the producer
    private RabbitMQJsonProducer rabbitMQJsonProducer;
    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    //Create Resend point
    //http://localhost:8080/api/v1/publish/message=hello
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent to Rabbit queue...");
    }



}
