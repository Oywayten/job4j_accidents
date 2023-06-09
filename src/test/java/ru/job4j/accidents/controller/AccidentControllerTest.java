package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.AppMain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void whenSaveAccidentShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(post("/saveAccident")
                        .param("name", "name1")
                        .param("text", "text1")
                        .param("address", "address1")
                        .param("typeId", "1")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));
    }

    @Test
    @WithMockUser
    public void whenFormUpdateAccidentShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/formUpdateAccident?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editAccident"));
    }

    @Test
    @WithMockUser
    public void whenUpdateAccidentShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(
                post("/updateAccident")
                        .param("id", "1")
                        .param("name", "name1")
                        .param("text", "text1")
                        .param("address", "address1")
                        .param("typeId", "1")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));
    }
}