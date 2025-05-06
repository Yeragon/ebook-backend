package org.example.loan;

import org.example.user.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ApiResponse<String> borrowBook(@RequestBody Map<String, String> payload) {
        UUID userId = UUID.fromString(payload.get("userId"));
        UUID ebookId = UUID.fromString(payload.get("ebookId"));

        LoanResult result = loanService.borrow(userId, ebookId);

        return switch (result) {
            case SUCCESS -> ApiResponse.success("Book loaned successfully");
            case USER_LIMIT_EXCEEDED -> ApiResponse.fail("You cannot borrow more than 10 books");
            case BOOK_OUT_OF_STOCK -> ApiResponse.fail("This book is out of stock");
            case ALREADY_BORROWED -> ApiResponse.fail("You've already borrowed this book");
        };
    }


    @PostMapping("/return")
    public ApiResponse<String> returnBook(@RequestBody Map<String, String> payload) {
        UUID userId = UUID.fromString(payload.get("userId"));
        UUID ebookId = UUID.fromString(payload.get("ebookId"));
        loanService.returnBook(userId, ebookId);
        return ApiResponse.success("Book returned successfully");
    }

    @GetMapping("/active/{userId}")
    public ApiResponse<List<Loan>> getActive(@PathVariable UUID userId) {
        return ApiResponse.success(loanService.getActiveLoans(userId));
    }

    @PostMapping("/cancel")
    public ApiResponse<String> cancelLoan(@RequestBody Map<String, String> payload) {
        UUID userId = UUID.fromString(payload.get("userId"));
        UUID ebookId = UUID.fromString(payload.get("ebookId"));
        boolean result = loanService.cancelLoan(userId, ebookId);
        if (result) {
            return ApiResponse.success("Loan cancelled successfully");
        } else {
            return ApiResponse.fail("No active loan found to cancel");
        }
    }


    @GetMapping("/due-soon/{userId}")
    public List<LoanDTO> getDueSoonLoans(@PathVariable String userId) {
        return loanService.getDueSoonLoansByUser(userId);
    }

}