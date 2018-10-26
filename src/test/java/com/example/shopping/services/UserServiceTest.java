package com.example.shopping.services;

import com.example.shopping.entity.User;
import com.example.shopping.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
    }

    @Test
    public void createUserSuccessfully() throws Exception {
        //Arrange
        doAnswer(returnsFirstArg()).when(userRepository).save(any(User.class));
        User user = new User();
        user.setUsername("paiizz").setPassword("1234");

        //Act
        User userResponse = userService.createUser(user);

        //Assert
        assertThat(userResponse.getUsername()).isEqualTo("paiizz");
        assertThat(userResponse.getPassword()).isEqualTo("1234");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getAllUser() throws Exception{
        //Arrange
        User user1 = new User();
        user1.setUsername("paiizz").setPassword("1234");
        User user2 = new User();
        user2.setUsername("trong").setPassword("1234");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));

        //Act
        List<User> listUserResponse = userService.getAllUser();

        //Assert
        assertThat(listUserResponse.get(0).getUsername()).isEqualTo("paiizz");
        assertThat(listUserResponse.get(0).getPassword()).isEqualTo("1234");
        assertThat(listUserResponse.get(1).getUsername()).isEqualTo("trong");
        assertThat(listUserResponse.get(1).getPassword()).isEqualTo("1234");

        verify(userRepository, times(1)).findAll();
    }


}