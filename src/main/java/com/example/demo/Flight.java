package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Flight {
    private Date departs;
    private List<PlaneTicket> tickets;

    @JsonFormat(pattern = "yyyy-MM-dd H:m")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<PlaneTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<PlaneTicket> tickets) {
        this.tickets = tickets;
    }

    // sub classes
    public static class PlaneTicket{
        private Passenger passenger;
        private int price;

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static class Passenger {

        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
