package edu.course.library;
public interface NotificationSender {
 // Notify the recipient; each implementation chooses the contact address.
 void send(User recipient,String message);
}
