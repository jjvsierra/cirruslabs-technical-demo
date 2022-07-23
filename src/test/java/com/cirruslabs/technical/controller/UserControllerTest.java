package com.cirruslabs.technical.controller;

import com.cirruslabs.technical.model.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    public static final String USER_NAME_MOCK = "userNameMock";
    public static final String WELCOME_MESSAGE = "Hello " + USER_NAME_MOCK + ", Welcome from";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnWelcomeMessage() throws Exception {
        this.mockMvc.perform(post("/users")
                        .content(asJsonString(mockUserDtoRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.message", containsString(WELCOME_MESSAGE)));
    }

    @Test
    public void shouldReturnUserNotEligibleErrorMessage() throws Exception {

        UserRequestDto mockUserRequest = mockUserDtoRequest();
        mockUserRequest.setIpAddress("invalidIpMock");
        this.mockMvc.perform(post("/users")
                        .content(asJsonString(mockUserRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo("User is not eligible to register")));
    }

    @Test
    public void shouldReturnInvalidPayloadErrorMessages() throws Exception {
        this.mockMvc.perform(post("/users")
                        .content(asJsonString(mockInvalidUserDtoRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userName", equalTo("Username must not be empty")))
                .andExpect(jsonPath("$.ipAddress", equalTo("IP address must not be empty")))
                .andExpect(jsonPath("$.password", equalTo("Password must be greater than 8 characters, containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .")));
    }

    private UserRequestDto mockUserDtoRequest() {
        return UserRequestDto.builder().userName(USER_NAME_MOCK)
                .password("passMock2342_")
                .ipAddress("192.206.151.131")
                .build();
    }

    private UserRequestDto mockInvalidUserDtoRequest() {
        return UserRequestDto.builder().userName(USER_NAME_MOCK)
                .userName(null)
                .password("password1_")
                .ipAddress("")
                .build();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}