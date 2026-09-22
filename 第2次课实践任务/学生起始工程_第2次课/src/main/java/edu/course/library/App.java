package edu.course.library;
public class App {
 public static void main(String[] args){
  runScenario(new SmsAdapter(new LegacySmsClient()));
  runScenario(new EmailNotificationSender());
  System.out.println("SMS and Email demos finished.");
 }
 private static void runScenario(NotificationSender notificationSender){
  InMemoryDatabase db=new InMemoryDatabase();
  db.saveUser(new User("U01","STUDENT","13800000000","u01@campus.edu"));
  db.saveBook(new BookCopy("B01"));
  BorrowService service=new BorrowService(db,notificationSender);
  service.borrowBook("U01","B01");
  if(db.findBook("B01").isAvailable()) throw new AssertionError("borrow");
  service.returnBook("U01","B01");
  if(!db.findBook("B01").isAvailable()) throw new AssertionError("return");
 }
}
