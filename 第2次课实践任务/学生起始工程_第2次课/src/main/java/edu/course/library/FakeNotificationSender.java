package edu.course.library;
public class FakeNotificationSender implements NotificationSender {
 private int callCount;
 private User lastRecipient;
 private String lastMessage;
 @Override public void send(User recipient,String message){
  callCount++;
  lastRecipient=recipient;
  lastMessage=message;
 }
 public int getCallCount(){return callCount;}
 public User getLastRecipient(){return lastRecipient;}
 public String getLastMessage(){return lastMessage;}
}
