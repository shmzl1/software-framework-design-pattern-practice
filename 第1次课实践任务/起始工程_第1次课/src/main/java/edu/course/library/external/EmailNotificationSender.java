package edu.course.library.external;

import edu.course.library.service.NotificationSender;

public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("[EMAIL] to=" + recipient + " message=" + message);
    }
}