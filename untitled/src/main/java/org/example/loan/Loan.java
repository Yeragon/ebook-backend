package org.example.loan;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private UUID id;
    private UUID userId;
    private UUID ebookId;
    private LocalDate rentalStartDate;
    private LocalDate expirationDate;
    private LocalDate returnDate;
    private String status; // active / returned / overdue

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getEbookId() { return ebookId; }
    public void setEbookId(UUID ebookId) { this.ebookId = ebookId; }

    public LocalDate getRentalStartDate() { return rentalStartDate; }
    public void setRentalStartDate(LocalDate rentalStartDate) { this.rentalStartDate = rentalStartDate; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
