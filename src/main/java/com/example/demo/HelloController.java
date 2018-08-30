package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/tasks")
    public String getTasks(){
        return "These are the tasks";
    }

    @PostMapping("/tasks")
    public String postTask(){
        return "You just posted a task";
    }

    @GetMapping("/math/pi")
    public String getPi(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String calculate(WebRequest webRequest) {
        Map<String, String[]> params = webRequest.getParameterMap();
        MathService mathService = new MathService();
        return mathService.calculate(params);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> queryString){
        MathService mathService = new MathService();
        return mathService.sum(queryString.get("n"));
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String calculateVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        int volume = length * width * height;
        return String.format("The volume of a %sx%sx%s rectangle is %d", length, width, height, volume);
    }


    @PostMapping(value = "/math/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculateArea(AreaObj areaObj) {
        return areaObj.calculateArea();
    }


    @GetMapping("/flights")
    public List<Flight> getFlights() {
        String departs = "2017-04-21 14:34";
        Flight flight1 = new Flight();
        Flight.Passenger passenger = new Flight.Passenger();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        Flight.PlaneTicket ticket = new Flight.PlaneTicket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);
        flight1.setDeparts(new SimpleDateFormat("yyyy-MM-dd").parse(departs, new ParsePosition(0)));
        flight1.setTickets(Arrays.asList(ticket));

        String departs2 = "2017-04-21 14:34";
        Flight flight2 = new Flight();
        Flight.Passenger passenger2 = new Flight.Passenger();
        passenger2.setFirstName("Some other name");
        passenger2.setLastName("");
        Flight.PlaneTicket ticket2 = new Flight.PlaneTicket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(400);
        flight2.setDeparts(new SimpleDateFormat("yyyy-MM-dd").parse(departs2, new ParsePosition(0)));
        flight2.setTickets(Arrays.asList(ticket2));

        return Arrays.asList(flight1, flight2);
    }

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        String departs = "2017-04-21 14:34";
        Flight result = new Flight();
        Flight.Passenger passenger = new Flight.Passenger();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        Flight.PlaneTicket ticket = new Flight.PlaneTicket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);
        result.setDeparts(new SimpleDateFormat("yyyy-MM-dd").parse(departs, new ParsePosition(0)));
        result.setTickets(Arrays.asList(ticket));
        return result;
    }

    @PostMapping("/flights/tickets/total")
    public String sumTickets(@RequestBody FlightTicket flightTicket){
        return String.format("{\n" +
                "               \"result\": %d\n" +
                              "}", flightTicket.ticketTotal());
    }
}