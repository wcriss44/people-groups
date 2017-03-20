package com.theironyard.novauc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleGroupsApplicationTests {


	@Autowired
	WebApplicationContext wap;

	@Autowired
	UserRepository users;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}
	@Test
	public void testAddUser() throws Exception {
		Assert.assertTrue(users.count() == 1);
		User user = new User();
		user.setName("Alice");
		user.setAddress("17 Princess St");
		user.setEmail("alice@gmail.com");
		user.setCellphone("333-444-5555");
		user.setServicebranch("army");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.content(json)
						.contentType("application/json")
		);
		Assert.assertTrue(users.count() == 2);
	}
	@Test
	public void testGetUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(users.findOne(1));

			ResultActions ra =  mockMvc.perform(
			 MockMvcRequestBuilders.get("/user/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(json));
	}
	@Test
	public void testGetUsers() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(users.findAll());

		ResultActions ra =  mockMvc.perform(
				MockMvcRequestBuilders.get("/user"))
				.andExpect(status().isOk())
				.andExpect(content().json(json));
	}
	@Test
	public void testUpdateUser() throws Exception {
		Assert.assertTrue(users.count() == 2);
		Assert.assertTrue(users.findOne(2).getName().equals("Alice"));
		User user = new User();
		user.setId(2);
		user.setName("Bob");
		user.setAddress("17 NEW Princess St");
		user.setEmail("bob@gmail.com");
		user.setCellphone("333-444-5555");
		user.setServicebranch("navy");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/user")
						.content(json)
						.contentType("application/json")
		);
		Assert.assertTrue(users.count() == 2);
		Assert.assertTrue(users.findOne(2).getName().equals("Bob"));
	}
	@Test
	public void testDeleteUser() throws Exception {
		Assert.assertTrue(users.count() == 2);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/user/1")
		);

		Assert.assertTrue(users.count() == 1);
	}
	@Test
	public void contextLoads() {
	}

}
