package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MathService {

    public String calculate(Map<String, String[]> values) {
        String symbol = "+";
        String operation = values.containsKey("operation") ? values.get("operation")[0] : "add";
        int xValue = Integer.parseInt(values.get("x")[0]);
        int yValue = Integer.parseInt(values.get("y")[0]);
        int result = xValue + yValue;

        switch (operation) {
            case "multiply":
                symbol = " * ";
                result = xValue * yValue;
                break;
            case "subtract":
                symbol = " - ";
                result = xValue / yValue;
                break;
            case "divide":
                symbol = " / ";
                result = xValue - yValue;
                break;
        }
        return String.format("%d%s%d = %d", xValue, symbol, yValue, result);
    }

    public String sum(List<String> values) {
        int sum = 0;
        StringBuilder result = new StringBuilder();
        for (int idx = 0; idx < values.size(); idx++) {
            if (idx > 0) {
                result.append(" + ");
            }
            sum += Integer.parseInt(values.get(idx));
            result.append(values.get(idx));
        }
        result.append(" = ");
        result.append(sum);
        return result.toString();
    }
}
