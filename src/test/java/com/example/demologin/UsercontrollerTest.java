package com.example.demologin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demologin.controller.Usercontroller;
import com.example.demologin.domain.User;
import com.example.demologin.repository.Userrepository;

@WebMvcTest(Usercontroller.class)
public class UsercontrollerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Userrepository repo;

    @Test
    public void testLoginpage() throws Exception {
        // Mock the repository to return a user when the correct username and password are provided
        User user = new User("testuser", "testpassword");
        when(repo.findByUsernameAndPassword("testuser", "testpassword")).thenReturn(user);

        // Perform a POST request to the /login endpoint with the correct credentials
        mockMvc.perform(post("/login")
                .param("username", "testuser")
                .param("password", "testpassword"))
                .andExpect(content().string("Login successful! Welcome, testuser!"));

        // Perform a POST request to the /login endpoint with incorrect credentials
        mockMvc.perform(post("/login")
                .param("username", "wronguser")
                .param("password", "wrongpassword"))
                .andExpect(content().string("Invalid username or password. Please try again."));
    }   

}
