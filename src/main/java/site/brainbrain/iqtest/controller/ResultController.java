package site.brainbrain.iqtest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.brainbrain.iqtest.controller.dto.CreateResultRequest;
import site.brainbrain.iqtest.service.CertificateService;
import site.brainbrain.iqtest.service.EmailService;
import site.brainbrain.iqtest.service.PaymentService;
import site.brainbrain.iqtest.service.ScoreService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ResultController {

    private final PaymentService paymentService;
    private final CertificateService certificateService;
    private final ScoreService scoreService;
    private final EmailService emailService;

    @PostMapping("/")
    public void create(@RequestParam CreateResultRequest request) {
        paymentService.pay(request);
        scoreService.calculate(request);
        certificateService.generate(request);
        emailService.send(request);
    }

}
