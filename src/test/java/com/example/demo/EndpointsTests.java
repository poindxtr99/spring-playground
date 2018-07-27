package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class EndpointsTests {

    @Autowired
    private MockMvc mockMvc;

    MockHttpServletRequestBuilder request = post("/math/area").contentType(MediaType.APPLICATION_FORM_URLENCODED);

    @Test
    public void testPiEndpoint() throws Exception {
        this.mockMvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculate() throws Exception {
        this.mockMvc.perform(get("/math/calculate?operation=multiply&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testSum() throws Exception {
        this.mockMvc.perform(post("/math/sum?n=4&n=5&n=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolume() throws Exception {
        this.mockMvc.perform(post("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testCircleArea() throws Exception {
        request.param("type", "circle")
                .param("radius", "4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.265482"));
    }

    @Test
    public void testRectArea() throws Exception {
        request.param("type", "rectangle")
                .param("height", "7")
                .param("width", "4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void testCalculateAreaFail() throws Exception {
        request.param("type", "circle")
                .param("height", "4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}
