package edu.course.library;
public class BorrowService {
 private final InMemoryDatabase database; private final LegacySmsClient smsClient;
 public BorrowService(InMemoryDatabase database,LegacySmsClient smsClient){this.database=database;this.smsClient=smsClient;}
 public void borrowBook(String userId,String bookId){
  User user=database.findUser(userId); if(user==null) throw new IllegalArgumentException("User not found");
  BookCopy book=database.findBook(bookId); if(book==null) throw new IllegalArgumentException("Book not found");
  long activeBorrowCount=database.countActiveBorrowings(userId);
  user.checkBorrowLimit(activeBorrowCount);
  book.borrow(); database.saveBook(book);
  database.saveBorrowRecord(new BorrowRecord(userId,bookId));
  smsClient.sendText(user.getPhone(),"Borrow success: "+bookId,1);
 }
 public void returnBook(String userId,String bookId){
  BorrowRecord record=database.findBorrowRecord(userId,bookId);
  if(record==null||record.isReturned()) throw new IllegalStateException("No active borrowing");
  BookCopy book=database.findBook(bookId); record.markReturned(); book.returnCopy(); database.saveBook(book);
 }
}
