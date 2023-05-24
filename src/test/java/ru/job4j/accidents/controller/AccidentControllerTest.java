package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.AppMain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Oywayten 24.05.2023.
 */
@SpringBootTest(classes = AppMain.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenCreateAccidentShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void whenFormUpdateAccidentShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/formUpdateAccident?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editAccident"));
    }

}