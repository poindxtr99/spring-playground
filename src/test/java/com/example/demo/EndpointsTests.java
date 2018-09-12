package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

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

    @Test
    public void testFlightCall() throws Exception {
        this.mockMvc.perform(get("/flights/flight").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"departs\": \"2017-04-21 6:0\",\n" +
                        "    \"tickets\": [\n" +
                        "        {\n" +
                        "            \"passenger\": {\n" +
                        "                \"firstName\": \"Some name\",\n" +
                        "                \"lastName\": \"Some other name\"\n" +
                        "            },\n" +
                        "            \"price\": 200\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"));
    }

    @Test
    public void testFlightsCall() throws Exception {
        this.mockMvc.perform(get("/flights").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"departs\": \"2017-04-21 6:0\",\n" +
                        "        \"tickets\": [\n" +
                        "            {\n" +
                        "                \"passenger\": {\n" +
                        "                    \"firstName\": \"Some name\",\n" +
                        "                    \"lastName\": \"Some other name\"\n" +
                        "                },\n" +
                        "                \"price\": 200\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"departs\": \"2017-04-21 6:0\",\n" +
                        "        \"tickets\": [\n" +
                        "            {\n" +
                        "                \"passenger\": {\n" +
                        "                    \"firstName\": \"Some other name\",\n" +
                        "                    \"lastName\": \"\"\n" +
                        "                },\n" +
                        "                \"price\": 400\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void testTicketTotalLiteral() throws  Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"tickets\": [\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Some name\",\n" +
                        "          \"lastName\": \"Some other name\"\n" +
                        "        },\n" +
                        "        \"price\": 200\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Name B\",\n" +
                        "          \"lastName\": \"Name C\"\n" +
                        "        },\n" +
                        "        \"price\": 150\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "  \"result\": 350" +
                        "}"));
    }

    @Test
    public void testTicketTotalSerialized() throws  Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> passenger1 = new HashMap<String, Object>(){
            {
                put("firstName", "Hercules");
                put("lastName", 57);
            }
        };
        HashMap<String, Object> passenger2 = new HashMap<String, Object>(){
            {
                put("firstName", "She-Ra");
                put("lastName", 37);
            }
        };

        HashMap<String, Object> ticket1 = new HashMap<String, Object>(){
            {
                put("passenger", passenger1);
                put("price", 200);
            }
        };
        HashMap<String, Object> ticket2 = new HashMap<String, Object>(){
            {
                put("passenger", passenger2);
                put("price", 150);
            }
        };

        Object[] ticketData = {ticket1, ticket2};
        HashMap<String, Object> tickets = new HashMap<String, Object>(){
            {
                put("tickets", ticketData);
            }
        };

        String json = objectMapper.writeValueAsString(tickets);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().json("{" +
                "  \"result\": 350" +
                "}"));
    }

    @Test
    public void testTicketTotalFileFixture() throws  Exception {
        String json = getJSON("/ticketdata.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "  \"result\": 350" +
                        "}"));
    }


    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        String file = url.getFile();

        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
