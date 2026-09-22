package edu.course.library;
public class BorrowService {
 private final InMemoryDatabase database; private final LegacySmsClient smsClient;
 public BorrowService(InMemoryDatabase database,LegacySmsClient smsClient){this.database=database;this.smsClient=smsClient;}
 public void borrowBook(String userId,String bookId){
  User user=database.findUser(userId); if(user==null) throw new IllegalArgumentException("User not found");
  BookCopy book=database.findBook(bookId); if(book==null) throw new IllegalArgumentException("Book not found");
  if(!book.isAvailable()) throw new IllegalStateException("Book unavailable");
  long active=database.countActiveBorrowings(userId);
  int limit="TEACHER".equals(user.getType())?10:5;
  if(active>=limit) throw new IllegalStateException("Borrow limit reached");
  book.setAvailable(false); database.saveBook(book);
  database.saveBorrowRecord(new BorrowRecord(userId,bookId));
  smsClient.sendText(user.getPhone(),"Borrow success: "+bookId,1);
 }
 public void returnBook(String userId,String bookId){
  BorrowRecord record=database.findBorrowRecord(userId,bookId);
  if(record==null||record.isReturned()) throw new IllegalStateException("No active borrowing");
  BookCopy book=database.findBook(bookId); record.markReturned(); book.setAvailable(true); database.saveBook(book);
 }
}
