package com.theironyard.novauc.controllers;

import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.services.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by octavio on 3/17/17.
 */

@RestController
@Api(value="SSA API", description="AAF - Acronyms Are Fun")
public class PeopleGroupsController {

    @Autowired
    UserRepository users;

    @ApiOperation(value = "Find the SSA of all users",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Good things come to those who dont have to wait"),
            @ApiResponse(code = 401, message = "Bad things come to those who do not have access"),
            @ApiResponse(code = 403, message = "Even worse things come to those who eat forbidden fruit"),
            @ApiResponse(code = 404, message = "Even even worse things come to those who can't find what they look for")
    }
    )
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) users.findAll();
    }
    @ApiOperation(value = "Put a user under government watch",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Good things come to those who dont have to wait"),
            @ApiResponse(code = 401, message = "Bad things come to those who do not have access"),
            @ApiResponse(code = 403, message = "Even worse things come to those who eat forbidden fruit"),
            @ApiResponse(code = 404, message = "Even even worse things come to those who can't find what they look for")
    }
    )
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        users.save(user);
    }
    @ApiOperation(value = "Change the status of a tracked individual",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Good things come to those who dont have to wait"),
            @ApiResponse(code = 401, message = "Bad things come to those who do not have access"),
            @ApiResponse(code = 403, message = "Even worse things come to those who eat forbidden fruit"),
            @ApiResponse(code = 404, message = "Even even worse things come to those who can't find what they look for")
    }
    )
    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        users.save(user);
    }
    @ApiOperation(value = "Remove someone from Uncle Sam's database",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Good things come to those who dont have to wait"),
            @ApiResponse(code = 401, message = "Bad things come to those who do not have access"),
            @ApiResponse(code = 403, message = "Even worse things come to those who eat forbidden fruit"),
            @ApiResponse(code = 404, message = "Even even worse things come to those who can't find what they look for")
    }
    )
    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);
    }
    @ApiOperation(value = "Leverage the power of IDs to find a specific one",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Good things come to those who dont have to wait"),
            @ApiResponse(code = 401, message = "Bad things come to those who do not have access"),
            @ApiResponse(code = 403, message = "Even worse things come to those who eat forbidden fruit"),
            @ApiResponse(code = 404, message = "Even even worse things come to those who can't find what they look for")
    }
    )
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") int id) {
        return users.findOne(id);
    }
    @PostConstruct
    public void init() {

        if (users.count() == 0) {
            User user = new User("Mike", "123 Main ST", "southcarolina@gmail.com", "123456", "Army");
            users.save(user);
        }
        }
    }
