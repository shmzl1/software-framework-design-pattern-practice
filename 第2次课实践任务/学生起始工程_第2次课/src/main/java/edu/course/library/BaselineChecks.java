package edu.course.library;
public class BaselineChecks {
 public static void main(String[] args){
  normal(); duplicate(); limit(); System.out.println("ALL BASELINE CHECKS PASSED");
 }
 static void normal(){Fixture f=new Fixture(); f.service.borrowBook("U01","B01"); check(!f.db.findBook("B01").isAvailable(),"borrow"); f.service.returnBook("U01","B01"); check(f.db.findBook("B01").isAvailable(),"return");}
 static void duplicate(){Fixture f=new Fixture(); f.service.borrowBook("U01","B01"); expect(()->f.service.borrowBook("U01","B01"));}
 static void limit(){InMemoryDatabase db=new InMemoryDatabase(); db.saveUser(new User("U01","STUDENT","1","e")); BorrowService s=new BorrowService(db,new LegacySmsClient()); for(int i=1;i<=6;i++)db.saveBook(new BookCopy("B0"+i)); for(int i=1;i<=5;i++)s.borrowBook("U01","B0"+i); expect(()->s.borrowBook("U01","B06"));}
 static void expect(Runnable r){try{r.run();throw new AssertionError("expected failure");}catch(IllegalStateException ok){}}
 static void check(boolean c,String m){if(!c)throw new AssertionError(m);}
 static class Fixture{final InMemoryDatabase db=new InMemoryDatabase(); final BorrowService service; Fixture(){db.saveUser(new User("U01","STUDENT","1","e"));db.saveBook(new BookCopy("B01"));service=new BorrowService(db,new LegacySmsClient());}}
}
