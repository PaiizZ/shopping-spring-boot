package com.example.shopping.services.user;

import com.example.shopping.entities.shopping.User;
import com.example.shopping.exceptions.UserNotFoundException;
import com.example.shopping.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void createUserSuccess() {
        //Arrange
        User user = new User().setUsername("paiizz").setPassword("1234");
        when(userRepository.save(any(User.class))).thenReturn(user);


        //Act
        User userResponse = userService.createUser(user);

        //Assert
        assertThat(userResponse.getUsername()).isEqualTo("paiizz");
        assertThat(userResponse.getPassword()).isEqualTo("1234");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getAllUser() {
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

    @Test
    public void getUserById() {
        //Arrange
        User user = new User();
        user.setUsername("paiizz").setPassword("1234");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //Act
        User userResponse = userService.getUserById(anyLong());

        //Assert
        assertThat(userResponse.getUsername()).isEqualTo("paiizz");
        assertThat(userResponse.getPassword()).isEqualTo("1234");

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByIdNotFound() {
        //Act
        Object userNotFoundException = userService.getUserById(anyLong());

        //Assert
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void loadUserByUsername() {
        //Arrange
        User user = new User().setUsername("paiizz").setPassword("1234");
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        //Act
        UserDetails userResponse = userService.loadUserByUsername(anyString());

        //Assert
        assertThat(userResponse.getUsername()).isEqualTo("paiizz");
        assertThat(userResponse.getPassword()).isEqualTo("1234");
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameNotFound() {
        //Arrange
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        //Act
        UserDetails userResponse = userService.loadUserByUsername(anyString());

        //Assert
        verify(userRepository, times(1)).findByUsername(anyString());
    }
}