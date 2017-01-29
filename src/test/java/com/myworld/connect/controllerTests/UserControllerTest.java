package com.myworld.connect.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myworld.connect.controllers.UserController;
import com.myworld.connect.model.User;
import com.myworld.connect.services.UserNotFoundException;
import com.myworld.connect.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @Test
    public void shouldPostUserDetails() throws Exception {
        User user = new User(1, "test@email.com", "test");
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user)))
                .andExpect(status().isCreated());
        verify(userService).save(any(User.class));
    }

    @Test
    public void shouldGetDetails() throws Exception {
        when(userService.getByEmailID("test@email.com")).thenReturn(new User(1, "test@email.com", "test"));
        mockMvc.perform(get("/user/test@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userID", is(1)))
                .andExpect(jsonPath("$.emailID", is("test@email.com")));
    }

    @Test
    public void shouldUpdateUserDetails() throws Exception, UserNotFoundException {
        User user = new User(1, "test@email.com", "test");
        mockMvc.perform(put("/user/test@email.com").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(user)))
                .andExpect(status().isAccepted());
        verify(userService, times(1)).update(any(User.class), eq("test@email.com"));
    }


    @Test
    public void shouldDeleteUserDetails() throws Exception, UserNotFoundException {
        mockMvc.perform(delete("/user/test@email.com"))
                .andExpect(status().isOk());
        verify(userService, times(1)).delete("test@email.com");
    }
}