package edu.course.library;
public class BaselineChecks {
 public static void main(String[] args){
  normal(); duplicate(); limit(); notification(); System.out.println("ALL BASELINE CHECKS PASSED");
 }
 static void normal(){Fixture f=new Fixture(); f.service.borrowBook("U01","B01"); check(!f.db.findBook("B01").isAvailable(),"borrow"); f.service.returnBook("U01","B01"); check(f.db.findBook("B01").isAvailable(),"return");}
 static void duplicate(){
  Fixture f=new Fixture();
  f.service.borrowBook("U01","B01");
  expect(()->f.service.borrowBook("U01","B01"));
  check(f.notifications.getCallCount()==1,"duplicate must not notify");
 }
 static void limit(){
  InMemoryDatabase db=new InMemoryDatabase();
  db.saveUser(new User("U01","STUDENT","1","e"));
  FakeNotificationSender notifications=new FakeNotificationSender();
  BorrowService s=new BorrowService(db,notifications);
  for(int i=1;i<=6;i++)db.saveBook(new BookCopy("B0"+i));
  for(int i=1;i<=5;i++)s.borrowBook("U01","B0"+i);
  expect(()->s.borrowBook("U01","B06"));
  check(notifications.getCallCount()==5,"limit failure must not notify");
 }
 static void notification(){
  Fixture f=new Fixture();
  check(f.notifications.getCallCount()==0,"no notification before borrow");
  f.service.borrowBook("U01","B01");
  check(f.notifications.getCallCount()==1,"one notification per successful borrow");
  check(f.notifications.getLastRecipient()==f.db.findUser("U01"),"notification recipient");
  check("Borrow success: B01".equals(f.notifications.getLastMessage()),"notification message");
  f.service.returnBook("U01","B01");
  check(f.notifications.getCallCount()==1,"return must not notify");
 }
 static void expect(Runnable r){try{r.run();throw new AssertionError("expected failure");}catch(IllegalStateException ok){}}
 static void check(boolean c,String m){if(!c)throw new AssertionError(m);}
 static class Fixture{final InMemoryDatabase db=new InMemoryDatabase(); final FakeNotificationSender notifications=new FakeNotificationSender(); final BorrowService service; Fixture(){db.saveUser(new User("U01","STUDENT","1","e"));db.saveBook(new BookCopy("B01"));service=new BorrowService(db,notifications);}}
}
