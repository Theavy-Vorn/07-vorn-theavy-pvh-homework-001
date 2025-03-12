package org.example.springhomework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Ticket {

    private int Id;
    private String passengerName;
    private LocalDate travelDate;
    private String sourceStation;
    private double price;
    private Boolean paymentStatus;
    private Boolean ticketStatus;
    private String seatNumber;
}
