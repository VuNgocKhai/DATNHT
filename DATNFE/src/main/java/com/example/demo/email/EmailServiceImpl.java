package com.example.demo.email;

import com.example.demo.email.service.EmailService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendOtp(String emailNhan) {
        SimpleMailMessage message = new SimpleMailMessage();
        String tieuDe = "OTP";
        String otp = genOtp();
        String noiDung = "OTP của bạn là " + otp;
        message.setTo(emailNhan);
        message.setSubject(tieuDe);
        message.setText(noiDung);
        otpMap.put(emailNhan, new OTP(emailNhan, otp, System.currentTimeMillis()));
        emailSender.send(message);
    }

    Map<String, OTP> otpMap = new HashMap<>();
    final Integer length = 6;//do dai otp

    String genOtp() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Sinh số ngẫu nhiên từ 0 đến 9
            otp.append(digit);
        }
        return otp.toString();
    }

    @Override
    public Boolean isValidOtp(String email, String otp) {
        OTP Otp = otpMap.get(email);
        if (otp.equals(Otp.getOtp())) {
            long currentTime = System.currentTimeMillis();
            long fiveMinutesInMillis = 5 * 60 * 1000; // 5 phút trong mili giây
            if (Otp.getCreationTime() + fiveMinutesInMillis >= currentTime) {
                return true;
            }
        }
        return false;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class OTP {
        private String email; // ID của người dùng
        private String otp; // Mã OTP
        private long creationTime; // Thời gian tạo OTP
    }
}
