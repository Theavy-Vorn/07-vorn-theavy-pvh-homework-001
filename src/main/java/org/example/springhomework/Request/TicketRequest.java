package org.example.springhomework.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
public class TicketRequest {
    private String passengerName;
    private LocalDate travelDate;
    private String sourceStation;
    private double price;
    private Boolean paymentStatus;
    private Boolean ticketStatus;
    private String seatNumber;

}