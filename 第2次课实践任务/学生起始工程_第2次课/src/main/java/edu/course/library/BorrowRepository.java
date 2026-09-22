package edu.course.library;
public interface BorrowRepository {
 User findUser(String id);
 BookCopy findBook(String id);
 long countActiveBorrowings(String userId);
 void saveBook(BookCopy book);
 void saveBorrowRecord(BorrowRecord record);
 BorrowRecord findBorrowRecord(String userId,String bookId);
}
