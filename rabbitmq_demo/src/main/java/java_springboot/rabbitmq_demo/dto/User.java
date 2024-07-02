package java_springboot.rabbitmq_demo.dto;

import lombok.Data;

//dto class to facilitate serialization and deserialization

//@Data to automatically generate getter and setter methods
@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
}
