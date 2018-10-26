package com.example.shopping.services;

import com.example.shopping.entity.User;
import com.example.shopping.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

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
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void getAllUser() {

    }


}