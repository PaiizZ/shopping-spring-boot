package com.example.shopping.controllers;

import com.example.shopping.entity.User;
import com.example.shopping.services.user.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createUserSuccessfully() throws Exception {
        //Arrange
        User user = new User();
        user.setUsername("paiizz").setPassword("1234");
        when(userServiceImpl.createUser(any(User.class))).thenReturn(user);

        //Act
        ResultActions result = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user)));

        //Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("paiizz")))
                .andExpect(jsonPath("$.password", is("1234")));
        verify(userServiceImpl, times(1)).createUser(any(User.class));

    }


    @Test
    public void getAllUser() throws Exception{
        //Arrange
        User user1 = new User();
        user1.setUsername("paiizz").setPassword("1234");
        User user2 = new User();
        user2.setUsername("trong").setPassword("1234");
        when(userServiceImpl.getAllUser()).thenReturn(Arrays.asList(user1,user2));

        //Act
        ResultActions result = mockMvc.perform(get("/api/v1/users"));

        //Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is("paiizz")))
                .andExpect(jsonPath("$[0].password", is("1234")))
                .andExpect(jsonPath("$[1].username", is("trong")))
                .andExpect(jsonPath("$[1].password", is("1234")));
        verify(userServiceImpl, times(1)).getAllUser();
    }
}