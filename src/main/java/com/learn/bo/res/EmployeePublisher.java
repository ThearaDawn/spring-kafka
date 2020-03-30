package com.learn.bo.res;

import com.learn.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeePublisher {

    private MessageChannel output;

    @Autowired
    public EmployeePublisher(MessageChannel output) {
        this.output = output;
    }

    @PostMapping
    public Employee sendEmployee(@RequestBody Employee employee) {
        output.send(MessageBuilder.withPayload(employee).build());
        return employee;
    }
}
