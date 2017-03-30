package com.theironyard.novauc.controllers;

import com.theironyard.novauc.entities.PbUser;
import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.services.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
            // This part of the method does a GET request on Peter's API, and then builds an object out of it
            RestTemplate restTemplate = new RestTemplate();
            PbUser[] pbUserArr = restTemplate.getForObject("https://immense-lowlands-84747.herokuapp.com/user", PbUser[].class);
            PbUser pbUser = null;
            for (PbUser p: pbUserArr){
                if (p.getId() == id){
                    pbUser = p;
                }
            }
            //This will return a null object if the request failed or an object if it was successful
            return pbUser;
        }
        @RequestMapping(path="/djuser/{id}", method=RequestMethod.POST)
        public void djaddUser(@PathVariable("id") int id){
            PbUser pbUser = getPbUser(id);

            if (pbUser != null) {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                // This part of the method verifies that an object was created, and if it was that object is added to D'Angelo's
                Map map = new HashMap();
                map.put("Content-Type", "application/json");

                headers.setAll(map);
                //This will add the body of the http request, setting all of the variables that are needed
                Map<String, String> req_payload = new HashMap<>();
                req_payload.put("email", pbUser.getEmailAddress());
                req_payload.put("address", pbUser.getAffiliation());
                req_payload.put("name", pbUser.getName());
                req_payload.put("phonenumber", pbUser.getPhone());
                req_payload.put("ssn", pbUser.getFlavor());

                //This is the actual request, filled with the body and header from above
                HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
                String url = "https://secure-retreat-36287.herokuapp.com/user";

                //This uses the URL and Request from above to generate a post request.
                ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
            }
        }
    @RequestMapping(path="/pbuser/{id}", method=RequestMethod.DELETE)
    public void deletePbUser(@PathVariable("id") int id){
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            // This part of the method verifies that an object was created, and if it was that object is added to D'Angelo's
            Map map = new HashMap();
            map.put("Content-Type", "application/json");
            restTemplate.exchange("https://immense-lowlands-84747.herokuapp.com/user/" + id, HttpMethod.DELETE, new HttpEntity<String>(headers), String.class);
    }
    @PostConstruct
    public void init() {

        if (users.count() == 0) {
            User user = new User("Mike", "123 Main ST", "southcarolina@gmail.com", "123456", "Army");
            users.save(user);
        }
        }
    }
