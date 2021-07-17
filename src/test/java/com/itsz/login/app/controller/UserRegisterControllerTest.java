package com.itsz.login.app.controller;

import com.itsz.login.app.service.UserRegisterService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest
@AutoConfigureMockMvc
class UserRegisterControllerTest {

    @MockBean
    private UserRegisterService userRegisterService;

    @Autowired
    private UserRegisterController userRegisterController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnValidErrorMessageWhenPostInvalidRequest() throws Exception {
        String user = "{\"username\": \"\", \"email\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Is.is("username not blank")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is("password not blank")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordConfirm", Is.is("passwordConfirm not blank")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnOKWhenPostValidRequest() throws Exception {
        String user = "{\"username\": \"jeremy gilbert\", \"email\" : \"bob@domain.com\",\"password\" : \"123\",\"passwordConfirm\" : \"123\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}