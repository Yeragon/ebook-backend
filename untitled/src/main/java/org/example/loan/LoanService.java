package org.example.loan;

import org.example.common.MailService;
import org.example.ebook.EbookMapper;
import org.example.user.User;
import org.example.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanMapper loanMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EbookMapper ebookMapper;

    public LoanService(LoanMapper loanMapper) {
        this.loanMapper = loanMapper;
    }

    public LoanResult borrow(UUID userId, UUID ebookId) {

        // Each person can borrow at most one of the same books
        Loan existing = loanMapper.findActiveLoanByUserAndEbook(userId, ebookId);
        if (existing != null) {
            return LoanResult.ALREADY_BORROWED;
        }
        // Each user can borrow a maximum of 10 books
        if (loanMapper.countActiveLoans(userId) >= 10) {
            return LoanResult.USER_LIMIT_EXCEEDED;
        }


        // Check the inventory quantity in the ebooks table
        int stock = ebookMapper.getStockByEbookId(ebookId);
        if (stock <= 0) {
            return LoanResult.BOOK_OUT_OF_STOCK;
        }

        Loan loan = new Loan();
        loan.setId(UUID.randomUUID());
        loan.setUserId(userId);
        loan.setEbookId(ebookId);
        loan.setRentalStartDate(LocalDate.now());
        loan.setExpirationDate(LocalDate.now().plusDays(30));
        loan.setStatus("active");

        loanMapper.insertLoan(loan);

        // inventory -1
        ebookMapper.decreaseStockById(ebookId);


        Optional<User> userOpt = userMapper.findById(userId);
        userOpt.ifPresent(user -> {
            String to = user.getEmail();
            String subject = "E-book borrowing successful reminder";
            String content = "You have successfully borrowed the e-book（tittle：" + ebookId + "）.Wish you a pleasant reading!";
            mailService.send(to, subject, content);
        });
        return LoanResult.SUCCESS;
    }


    public void returnBook(UUID userId, UUID ebookId) {
        // 1. Check whether there are borrowing records in the "active" status
        Loan loan = loanMapper.findActiveLoanByUserAndEbook(userId, ebookId);
        if (loan == null) {
            throw new RuntimeException("No active loan found for this user and book");
        }

        // 2. Update the borrowing record as returned（return_date + status）
        loanMapper.returnLoan(userId, ebookId);

        // 3. book inventory +1
        loanMapper.incrementEbookStock(ebookId);
    }


    public List<Loan> getActiveLoans(UUID userId) {
        return loanMapper.getActiveLoansByUser(userId);
    }

    public boolean cancelLoan(UUID userId, UUID ebookId) {
        Loan loan = loanMapper.findActiveLoanByUserAndEbook(userId, ebookId);
        if (loan == null) {
            return false;
        }
        loanMapper.updateLoanStatus(userId, ebookId, "cancelled");
        return true;
    }

    public List<LoanDTO> getDueSoonLoansByUser(String userId) {
        return loanMapper.getDueSoonLoansByUser(userId);
    }



}