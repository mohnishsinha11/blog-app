package com.camigos.blog.services.impl;

import com.camigos.blog.entities.User;
import com.camigos.blog.payloads.UserDto;
import com.camigos.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepo userRepo;

    public void setup() {

    }

    @Test
    public void given_User_Details_when_Create_User_Method_is_Invoked_then_Save_User() {
        // Input data
        UserDto inputUserDto = new UserDto();
        inputUserDto.setName("Harry Kane");
        inputUserDto.setEmail("harry@example.com");

        // Mock the behavior of ModelMapper and UserRepo
        User userEntity = new User();
        userEntity.setName("Harry Kane");
        userEntity.setEmail("harry@example.com");

        User savedUserEntity = new User();
        savedUserEntity.setId(1);
        savedUserEntity.setName("Harry Kane");
        savedUserEntity.setEmail("harry@example.com");

        // Mocking the ModelMapper behavior
        when(modelMapper.map(inputUserDto, User.class)).thenReturn(userEntity);

        // Mocking the UserRepo behavior
        when(userRepo.save(userEntity)).thenReturn(savedUserEntity);

        // Mocking the ModelMapper behavior
        when(modelMapper.map(savedUserEntity, UserDto.class)).thenReturn(inputUserDto);

        // Perform the method under test
        UserDto userResultDto = userServiceImpl.createUser(inputUserDto);

        // Assertions
        assertEquals(inputUserDto.getName(), userResultDto.getName());
        assertEquals(inputUserDto.getEmail(), userResultDto.getEmail());

        // Verify that the save method was called on the repository
        verify(userRepo, times(1)).save(userEntity);
    }

    @Test
    public void test_Get_All_Users() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Harry");
        user1.setEmail("harry@example.com");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Tim");
        user2.setEmail("tim@example.com");

        List<User> userList = Arrays.asList(user1, user2);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setName("Harry");
        userDto1.setEmail("harry@example.com");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2);
        userDto2.setName("Tim");
        userDto2.setEmail("Tim@example.com");

        List<UserDto> expectedUserDtos = Arrays.asList(userDto1, userDto2);

        // Mocking the UserRepo behavior
        when(userRepo.findAll()).thenReturn(userList);

        // Mocking the ModelMappers
        when(modelMapper.map(user1, UserDto.class)).thenReturn(userDto1);
        when(modelMapper.map(user2, UserDto.class)).thenReturn(userDto2);

        // Perform the method under test
        List<UserDto> resultUserDtos = userServiceImpl.getAllUsers();

        // Assertions
        assertEquals(expectedUserDtos, resultUserDtos);

        // Verify that the findAll method was called on the repository
        verify(userRepo, times(1)).findAll();
    }
}
