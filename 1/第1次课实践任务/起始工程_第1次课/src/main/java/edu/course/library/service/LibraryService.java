package edu.course.library.service;
import edu.course.library.external.LegacySmsClient;
import edu.course.library.model.*;
import edu.course.library.repository.InMemoryDatabase;
public class LibraryService {
    private final InMemoryDatabase database=new InMemoryDatabase();
    private final LegacySmsClient smsClient=new LegacySmsClient();

    public void addUser(User user){database.saveUser(user);} public void addBook(BookCopy book){database.saveBook(book);}
    public BookCopy findBook(String bookId){return database.findBook(bookId);} public long countActiveBorrowings(String userId){return database.countActiveBorrowings(userId);}

    public void borrowBook(String userId,String bookId){
        User user=database.findUser(userId); BookCopy book=database.findBook(bookId);
        if(user==null) throw new IllegalArgumentException("User not found");
        if(book==null||!book.isAvailable()) throw new IllegalStateException("Book unavailable");
        long active=database.countActiveBorrowings(userId);
        if("STUDENT".equals(user.getType())&&active>=5) throw new IllegalStateException("Borrow limit reached");
        book.borrow();
        database.saveBorrowRecord(new BorrowRecord(userId,bookId));
        String message="Borrow success: "+bookId;
        smsClient.sendText(user.getPhone(),message,1);
        System.out.println("AUDIT: "+userId+" borrowed "+bookId);
    }

    public void returnBook(String userId,String bookId){
        BorrowRecord record=database.findBorrowRecord(userId,bookId); BookCopy book=database.findBook(bookId);
        if(record==null||record.isReturned()) throw new IllegalStateException("Active borrowing not found");
        record.markReturned(); book.returnCopy(); System.out.println("AUDIT: "+userId+" returned "+bookId);
    }
}
