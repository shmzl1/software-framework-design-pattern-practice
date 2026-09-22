package edu.course.library;
public class EmailNotificationSender implements NotificationSender {
 @Override public void send(User recipient,String message){
  System.out.println("[EMAIL] "+recipient.getEmail()+" <- "+message);
 }
}
