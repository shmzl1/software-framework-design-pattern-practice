package edu.course.library;
public class App {
 public static void main(String[] args){
  InMemoryDatabase db=new InMemoryDatabase();
  db.saveUser(new User("U01","STUDENT","13800000000","u01@campus.edu"));
  db.saveBook(new BookCopy("B01"));
  BorrowService service=new BorrowService(db,new LegacySmsClient());
  service.borrowBook("U01","B01"); service.returnBook("U01","B01");
  System.out.println("V0 demo finished.");
 }
}
