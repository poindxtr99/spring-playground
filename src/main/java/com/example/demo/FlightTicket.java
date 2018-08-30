package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightTicket {

    private Ticket[] tickets;

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public int ticketTotal(){
        int result = 0;

        for(Ticket ticket : tickets) {
            result += ticket.getPrice();
        }
        return result;
    }
}

class Ticket {
    private Passenger passenger;
    private int price;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


class Passenger {
    private String firstName;
    private String lastName;

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}