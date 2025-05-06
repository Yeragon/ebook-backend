package org.example.loan;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface LoanMapper {

    void insertLoan(Loan loan);

    void returnLoan(@Param("userId") UUID userId, @Param("ebookId") UUID ebookId);

    List<Loan> getActiveLoansByUser(UUID userId);

    int countActiveLoans(UUID userId);

    int countActiveLoansByEbook(UUID ebookId);

    Loan findActiveLoanByUserAndEbook(@Param("userId") UUID userId, @Param("ebookId") UUID ebookId);

    List<Loan> findLoansExpiringInDays(int days);

    void updateLoanStatus(@Param("userId") UUID userId,
                          @Param("ebookId") UUID ebookId,
                          @Param("status") String status);

    List<LoanDTO> getDueSoonLoansByUser(String userId);

    void incrementEbookStock(@Param("ebookId") UUID ebookId);


}
