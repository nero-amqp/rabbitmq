package com.nero.www.rabbitmq.api;

import com.nero.www.rabbitmq.service.RabbitPublishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rabbitmq")
public class RabbitmqApi {

    @Autowired
    private RabbitPublishService rabbitService;

    @ApiOperation(value = "sendmessage", httpMethod = "GET", notes = "send message")
    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    public void sendMessage(@RequestParam String message){
        rabbitService.send("exchange1", null, message);
    }
}
