package org.example.loan;

import lombok.Data;
import java.util.Date;

@Data
public class LoanDTO {
    private String id;
    private String userId;
    private String ebookId;
    private Date rentalStartDate;
    private Date expirationDate;
    private Date returnDate;
    private String status;
    private String title;
    private String author;

    private boolean available;
}
