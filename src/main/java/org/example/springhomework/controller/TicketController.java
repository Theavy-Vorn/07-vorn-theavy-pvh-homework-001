package org.example.springhomework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.springhomework.Request.TicketRequest;
import org.example.springhomework.model.entity.ApiResponse;
import org.example.springhomework.model.entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private int Id = 1;

    public TicketController() {

        ticketList.add(new Ticket(Id++, "Theavy", LocalDate.now(), "Phnom Penh", 2.0, true, false, "D1"));
        ticketList.add(new Ticket(Id++, "Makara", LocalDate.now(), "Kandal", 3, true, false, "D2"));
        ticketList.add(new Ticket(Id++, "Kanika", LocalDate.now(), "Takeo", 4, false, true, "D3"));
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketList;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Ticket>> addTicket(@RequestBody Ticket postTicket) {
        postTicket.setId(Id++);
        ticketList.add(postTicket);
        ApiResponse<Ticket> apiResponse = new ApiResponse<>
                (true, "Insert Successfully", HttpStatus.OK, postTicket, LocalDateTime.now());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public List<Ticket> searchById(@PathVariable int id) {
        List<Ticket> searchId = new ArrayList<>();
        for (Ticket Id : ticketList) {
            if (Id.getId() == id) {
                searchId.add(Id);
            }
        }
        return searchId;
    }

    //@RequestParam ==?theavy
    @GetMapping("/name")
    public List<Ticket> searchByName(@RequestParam String name) {
        List<Ticket> searchName = new ArrayList<>();
        for (Ticket Name : ticketList) {
            if (Name.getPassengerName().toLowerCase().contains(name.toLowerCase())) {
                searchName.add(Name);
            }
        }
        return searchName;
    }

    @GetMapping("/filter")
    public List<Ticket> filterTickets(@RequestParam boolean status, @RequestParam LocalDateTime travelDate) {
        List<Ticket> filteredTickets = new ArrayList<>();
        for (Ticket filTicket : ticketList) {
            if (filTicket.getTicketStatus() == status && filTicket.getTravelDate().equals(travelDate)) {
                filteredTickets.add(filTicket);
            }
        }
        return filteredTickets;
    }

    @PutMapping("/{ticket_id}")
    public ResponseEntity<ApiResponse<Ticket>> updateSearch(@PathVariable int ticket_id, @RequestBody TicketRequest updateTicket) {
        for (Ticket upTicket : ticketList) {
            if (upTicket.getId() == ticket_id) {
                upTicket.setPassengerName(updateTicket.getPassengerName());
                upTicket.setTravelDate(updateTicket.getTravelDate());
                upTicket.setPrice(updateTicket.getPrice());
                upTicket.setSourceStation(updateTicket.getSourceStation());
                upTicket.setPrice(updateTicket.getPrice());
                upTicket.setPaymentStatus(updateTicket.getPaymentStatus());
                upTicket.setTicketStatus(updateTicket.getTicketStatus());
                upTicket.setSeatNumber(updateTicket.getSeatNumber());

                ApiResponse<Ticket> apiResponse = new ApiResponse<>
                        (true, "Updated Successfully", HttpStatus.OK, upTicket, LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Ticket not found", HttpStatus.NOT_FOUND, null, LocalDateTime.now()));
    }

    @DeleteMapping("/{ticket_id}")
    public ResponseEntity<ApiResponse<Ticket>> deleteTicket(@PathVariable int ticket_id) {
        for (Ticket delTicket : ticketList) {
            if (delTicket.getId() == ticket_id) {
                ticketList.remove(delTicket);
                ApiResponse<Ticket> apiResponse = new ApiResponse<>
                        (true, "Deleted Successfully", HttpStatus.OK, delTicket, LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Ticket not found", HttpStatus.NOT_FOUND, null, LocalDateTime.now()));
    }
}

