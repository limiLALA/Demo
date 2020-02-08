package com.ninn.demo.controller;

import com.ninn.demo.domain.User;
import com.ninn.demo.repository.UserRepository;
import com.ninn.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index() {
        return "hello";
    }

    @RequestMapping("/user/all")
    public List<User> listAll() {
        logger.info("list");
        return userService.listAll();
    }

    @RequestMapping("/user/{id:\\d+}")
    public User findById(@PathVariable("id") int id){
        User user = new User();
        user.setId(id);
        User ret = userService.selectFirst(user);
        logger.info(ret.toString());
        return userService.selectFirst(user);

    }

}
