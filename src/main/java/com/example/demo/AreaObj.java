package com.example.demo;

public class AreaObj {
    private double PI = 3.141592653589793;
    private String type = "";
    private int radius = -1;
    private int width = -1;
    private int height = -1;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String calculateArea() {
        String result = "Invalid";
        if (validateParams()) {
            switch (type) {
                case "rectangle":
                    result = String.format("Area of a %dx%d rectangle is %d", width, height, width*height);
                    break;
                case "circle":
                    result = String.format("Area of a circle with a radius of %d is %f", radius, PI*Math.pow(radius, 2));
                    break;
            }
        }
        return result;
    }

    boolean validateParams(){
        boolean result = false;
        if (type.equals("circle") || type.equals("rectangle")){
            result = type.equals("circle") ? radius != -1 : (height != -1 && width != -1);
        }
        return result;
    }
}
