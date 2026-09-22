package edu.course.library.external;

import edu.course.library.service.NotificationSender;

public class SmsNotificationSender implements NotificationSender {

    private final LegacySmsClient smsClient = new LegacySmsClient();

    @Override
    public void send(String phone, String message) {
        smsClient.sendText(phone, message, 1);
    }
}