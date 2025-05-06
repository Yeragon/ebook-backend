package org.example.loan;

import org.example.loan.Loan;
import org.example.loan.LoanMapper;
import org.example.user.User;
import org.example.user.UserMapper;
import org.example.common.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanReminderScheduler {

    private final LoanMapper loanMapper;
    private final UserMapper userMapper;
    private final MailService mailService;

    public LoanReminderScheduler(LoanMapper loanMapper, UserMapper userMapper, MailService mailService) {
        this.loanMapper = loanMapper;
        this.userMapper = userMapper;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 0 10 * * ?") // 每天上午10点触发
    public void sendDueSoonReminders() {
        List<Loan> loans = loanMapper.findLoansExpiringInDays(1); // 到期前1天
        for (Loan loan : loans) {
            userMapper.findById(loan.getUserId()).ifPresent(user -> {
                String to = user.getEmail();
                String subject = "【提醒】您借阅的电子书即将到期";
                String content = "您好，您借阅的电子书（ID：" + loan.getEbookId() + "）将于明日到期，请及时归还。";
                mailService.send(to, subject, content);
            });
        }
    }
}
