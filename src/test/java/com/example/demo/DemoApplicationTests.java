package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void mathServiceCalculateTest() {
		MathService mathService = new MathService();
        Map<String, String[]> values = new HashMap<>();
        values.put("operation", new String[]{"multiply"});
        values.put("x", new String[]{"4"});
        values.put("y", new String[]{"6"});
        assertEquals("4 * 6 = 24",mathService.calculate(values));
	}

	@Test
	public void mathServiceSumTest() {
		MathService mathService = new MathService();
        List<String> values = new ArrayList<>();
        values.add("4");
        values.add("5");
        values.add("6");
		assertEquals("4 + 5 + 6 = 15", mathService.sum(values));

	}
}
