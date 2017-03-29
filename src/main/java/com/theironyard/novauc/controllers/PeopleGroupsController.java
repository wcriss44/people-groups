package com.theironyard.novauc.controllers;

import com.theironyard.novauc.entities.PbUser;
import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.services.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(path ="/pbuser/{id}", method = RequestMethod.GET)
        public PbUser getPbUser(@PathVariable("id") int id){
            //This part of the method conducts a get request on Peter's application
            RestTemplate restTemplate = new RestTemplate();
            PbUser[] pbUserArr = restTemplate.getForObject("https://immense-lowlands-84747.herokuapp.com/user", PbUser[].class);
            PbUser pbUser = null;
            for (PbUser p: pbUserArr){
                if (p.getId() == id){
                    pbUser = p;
                }
            }
            //This part of the method checks to see if a successful get request was done. If so, it does a post request to D'Angelo
            if (pbUser != null){       MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap();
                map.put("Content-Type", "application/json");

                headers.setAll(map);

                Map<String, String> req_payload = new HashMap<>();
                req_payload.put("email", pbUser.getEmailAddress());
                req_payload.put("address", pbUser.getAffiliation());
                req_payload.put("name", pbUser.getName());
                req_payload.put("phonenumber", pbUser.getPhone());
                req_payload.put("ssn", pbUser.getFlavor());

                HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
                String url = "https://secure-retreat-36287.herokuapp.com/user";
            }
            //This will be null if no user was found by the get method, or it will return the object
            return pbUser;
        }
    @PostConstruct
    public void init() {

        if (users.count() == 0) {
            User user = new User("Mike", "123 Main ST", "southcarolina@gmail.com", "123456", "Army");
            users.save(user);
        }
        }
    }
