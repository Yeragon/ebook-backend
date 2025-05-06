package org.example.test;

import org.example.common.MailService;
import org.example.user.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailTestController {

    private final MailService mailService;

    public MailTestController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/test-mail")
    public ApiResponse<String> sendTestMail() {
        mailService.send("stephenli9903@gmail.com", "Congratulation", " 李兄，喺异地他乡漫漫长夜入面，我淨系想同你讲一句：靓仔，雷猴呀！！");
        return ApiResponse.success("邮件已发送成功");
    }

}
