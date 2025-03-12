package org.example.springhomework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.springhomework.Request.TicketRequest;
import org.example.springhomework.model.entity.Ticket;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private List<Ticket> ticketList = new ArrayList<>();
    private int Id =1;
    public TicketController(){

        ticketList.add(new Ticket(Id++, "Theavy", LocalDate.now(), "Phnom Penh", 2.0, true, false, "D1"));
        ticketList.add(new Ticket(Id++, "Makara", LocalDate.now(), "Kandal", 3, true, false, "D2"));
        ticketList.add(new Ticket(Id++, "Kanika", LocalDate.now(), "Takeo", 4, false, true, "D3"));
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketList;
    }
    @PostMapping
    public Ticket addTicket(@RequestBody Ticket postTicket){
        ticketList.add(postTicket);
        return postTicket;
    }
    @GetMapping("/{ticket-id}")
    public List<Ticket> searchById(@PathVariable int id,OutputStream outputStream) {
        List<Ticket> searchId = new ArrayList<>();
        for (Ticket Id : ticketList) {
           if(Id.getId()==id){
              searchId.add(Id);
           }
        }
        return  searchId;
    }
    //@RequestParam ==?theavy
    @GetMapping("/name")
    public List<Ticket> searchByName(@RequestParam String name){
        List<Ticket> searchName = new ArrayList<>();
        for(Ticket Name: ticketList){
            if(Name.getPassengerName().toLowerCase().contains(name.toLowerCase())){
                searchName.add(Name);
            }
        }
        return searchName;
    }

    @GetMapping("/filter")
    public Ticket filterTicket(@RequestParam boolean status, @RequestParam LocalDateTime travelDate){
        System.out.println(status);
        for(Ticket filTicket : ticketList){
            if(filTicket.getTicketStatus().equals(status) && filTicket.getTravelDate().equals(travelDate)){

            }
        }
//        return filterTicket;
        return null;
    }

    @PutMapping("/{ticket-id}")
    public Ticket updateSearch(@PathVariable int id , @RequestBody TicketRequest updateTicket){
        for(Ticket upTicket: ticketList ){
           if(upTicket.getId()==id){
                upTicket.setPassengerName(updateTicket.getPassengerName());
                upTicket.setTravelDate(updateTicket.getTravelDate());
                upTicket.setPrice(updateTicket.getPrice());
                upTicket.setSourceStation(updateTicket.getSourceStation());
                upTicket.setPrice(updateTicket.getPrice());
                upTicket.setPaymentStatus(updateTicket.getPaymentStatus());
                upTicket.setTicketStatus(updateTicket.getTicketStatus());
                upTicket.setSeatNumber(updateTicket.getSeatNumber());

                return upTicket;
           }

        }

        return null;
    }

    @DeleteMapping("/{ticket-id}")
    public String deleteTicket(@PathVariable int id){
       for(Ticket delTicket : ticketList){
           if(delTicket.getId()==id){
                ticketList.remove(delTicket);
           }
           break;
       }
       return "Delete success !";
    }

}
