package com.example.numberofislands.generatorservice.controller;

import com.example.numberofislands.generatorservice.entity.Islands;
import com.example.numberofislands.generatorservice.repository.IslandsRepository;
import com.example.numberofislands.generatorservice.service.GeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    private static final String URL = "/api/islands/";
    private Islands islands;
    private JacksonTester<Islands> json;
    @MockBean
    private GeneratorService generatorService;

    @MockBean
    private IslandsRepository islandsRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        int[][] data = new int[][]{{1, 0, 1, 0},
                {0, 0, 1, 1},
                {1, 0, 0, 0},
                {0, 1, 0, 1}};
        islands = new Islands(1L, data);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getIslands_ShouldReturnOne() throws Exception {
        // given
        String jsonExpected = json.write(islands).getJson();
        given(generatorService.getIslandsById(1L)).willReturn(Optional.of(islands));

        // when & then
        mvc.perform(get(URL + "{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonExpected));
    }

    @Test
    public void getIslands_ShouldReturnNotFoundStatus() throws Exception {
        mvc.perform(get(URL + "{id}", 2L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Not found islands with id: %d", 2L)));
    }

    @Test
    public void postIslands_ShouldReturnCreatedStatus() throws Exception {
        mvc.perform(post(URL)
                .param("rows", "5")
                .param("cols", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}