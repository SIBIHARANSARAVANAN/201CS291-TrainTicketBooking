package com.TrainTicket.ticketbooking;

import ch.qos.logback.core.model.Model;
import com.TrainTicket.ticketbooking.TrainTicket;
import com.TrainTicket.ticketbooking.TrainTicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {

    private final TrainTicketRepository ticketRepository;

    public <TrainTicketRepository> controller(TrainTicketRepository ticketRepository) {
        this.ticketRepository = (com.TrainTicket.ticketbooking.TrainTicketRepository) ticketRepository;
    }

    @GetMapping("/book-ticket")
    public String showBookingForm(Model model) {
        model.addText("ticket");
        return "booking-form";
    }
    @PostMapping("/book-ticket")
    public String processBookingForm(@ModelAttribute TrainTicket
                                                 ticket) {
        // Save the ticket to the database using the repository
        ticketRepository.save(ticket);
        return "redirect:/ticket-details/" + ticket.getTicketId();
    }

    @GetMapping("/ticket-details/{id}")
    public String showTicketDetails(@PathVariable Long id, Model model) {
        TrainTicket ticket = (TrainTicket) ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + id));
        model.addText("ticket");
        return "ticket-details";
    }

}
