package net.aimeizi.quartz.controller;


import com.kowalski.demo.IPAddressKowalski;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.MalformedObjectNameException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    @Autowired
//    Environment environment;
    @ResponseBody()
    @RequestMapping(value = "/say-hello.do", method = RequestMethod.GET)
    public String sayHello() throws MalformedObjectNameException, UnknownHostException {
        logger.info(IPAddressKowalski.getIpAddressAndPort());

        System.out.println(simpleDateFormat.format(new Date()) + " Hello quartz!");
        return "sayHello";
    }


    @ResponseBody()
    @RequestMapping(value = "/say-goodbye.do", method = RequestMethod.GET)
    public String sayGoodBye() throws MalformedObjectNameException, UnknownHostException {
        logger.info(IPAddressKowalski.getIpAddressAndPort());
        System.out.println(simpleDateFormat.format(new Date()) + " Good bye quartz!");
        return "Good bye";
    }
}
