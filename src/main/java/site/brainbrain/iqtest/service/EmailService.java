package site.brainbrain.iqtest.service;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.brainbrain.iqtest.controller.dto.CreateResultRequest;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void send(final CreateResultRequest request) {
        try {
            final MimeMessage message = mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(request.email());
            helper.setSubject("BrainBrain IQ 테스트 결과");
            helper.setText("첨부된 인증서 및 보고서를 확인해주세요.");

            // Todo: 인증서 및 보고서 첨부

            mailSender.send(message);
        } catch (Exception e) {
            throw new MailException("이메일 전송에 실패했습니다.");
        }
    }
}
