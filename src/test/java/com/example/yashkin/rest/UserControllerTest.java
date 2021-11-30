package com.example.yashkin.rest;

import static org.hamcrest.Matchers.containsString;

import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserResponseDto;
import org.hibernate.annotations.SQLDelete;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithUserDetails("admin")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class UserControllerTest {

/*    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.2")
            .withUsername(POSTGRES_USERNAME)
            .withPassword(POSTGRES_PASSWORD);*/

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mvc;
/*    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext webApplicationContext;*/

/*    @Before()
    public void setup()
    {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @WithUserDetails("admin")
    @Test
    public void test() throws Exception {
        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")));
    }

    @Test
    public void accessDeniedTest() throws Exception {
        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @WithUserDetails("admin")
    @Test
    public void correctLoginTest() throws Exception {
        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithUserDetails("admin")
    @Test
    public void authenticatedTest() throws Exception {
        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @WithUserDetails("admin")
    @Test
    public void getUsers() {

    }

    @WithUserDetails("admin")
    @Test
    public void getUserById() {
    }

    @WithUserDetails("admin")
    @Test
    public void addUser() {
    }

    @WithUserDetails("admin")
    @Test
    public void updateUser() {
    }

    @WithUserDetails("admin")
    @Test
    public void deleteUser() {
    }

    @AfterClass
    public static void afterClass() throws Exception {

    }
}