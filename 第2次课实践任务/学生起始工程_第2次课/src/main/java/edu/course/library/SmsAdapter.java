package edu.course.library;
public class SmsAdapter implements NotificationSender {
 private final LegacySmsClient smsClient;
 public SmsAdapter(LegacySmsClient smsClient){this.smsClient=smsClient;}
 @Override public void send(User recipient,String message){
  smsClient.sendText(recipient.getPhone(),message,1);
 }
}
