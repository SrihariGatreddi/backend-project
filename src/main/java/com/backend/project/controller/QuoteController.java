package com.backend.project.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin(origins = "*")  // allow frontend from any origin (fine for learning)
public class QuoteController {

    private final List<String> quotes = Arrays.asList(
            "Believe in yourself and all that you are.",
            "Every day is a second chance.",
            "Dream big, work hard, stay humble.",
            "Success is the sum of small efforts repeated daily.",
            "Code. Sleep. Repeat.",
            "Great things never come from comfort zones."
    );

    private final Random random = new Random();

    // GET /api/quotes → returns all quotes
    @GetMapping
    public List<String> getAllQuotes() {
        return quotes;
    }

    // GET /api/quotes/random → returns one random quote
    @GetMapping("/random")
    public String getRandomQuote() {
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}
