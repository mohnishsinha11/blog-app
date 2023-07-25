package com.camigos.blog.controllers;

import com.camigos.blog.payloads.UserDto;
import com.camigos.blog.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class) // This annotation enables MockMvc and initializes the controller
public class UserControllerTest {

    // Using MockBean instead of @Mock to mock the service
    @MockBean
    UserService userService;

    // Autowire MockMvc to perform requests and receive responses
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateUser_Success() throws Exception {
        UserDto inputUserDto = new UserDto();
        inputUserDto.setName("Harry");
        inputUserDto.setEmail("harry@example.com");

        String inputJson = objectMapper.writeValueAsString(inputUserDto);

        MvcResult mvcResult = mockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        // Additional assertions using MvcResult
        String responseContent = mvcResult.getResponse().getContentAsString();
        int responseStatus = mvcResult.getResponse().getStatus();

        // Verify that the createUser method of the UserService was called with the correct argument
        verify(userService, times(1)).createUser(inputUserDto);

        // Further assertions with MvcResult
        assertEquals("{\"id\":1,\"name\":\"Harry\",\"email\":\"harry@example.com\"}", responseContent);
        assertEquals(HttpStatus.CREATED.value(), responseStatus);
    }
}
