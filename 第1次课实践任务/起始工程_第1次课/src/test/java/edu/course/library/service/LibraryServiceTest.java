package edu.course.library.service;
import edu.course.library.model.*; import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class LibraryServiceTest {
    private LibraryService fixture(){ LibraryService s=new LibraryService(); s.addUser(new User("U001","Alice","STUDENT","13800000001")); s.addUser(new User("T001","Dr. Chen","TEACHER","13800000003")); for(int i=1;i<=12;i++) s.addBook(new BookCopy("B"+String.format("%03d",i),"Book "+i)); return s; }
    @Test void normalBorrow(){ LibraryService s=fixture(); s.borrowBook("U001","B001"); assertFalse(s.findBook("B001").isAvailable()); assertEquals(1,s.countActiveBorrowings("U001")); }
    @Test void unknownUser(){ LibraryService s=fixture(); assertThrows(IllegalArgumentException.class,()->s.borrowBook("U404","B001")); }
    @Test void unavailableBook(){ LibraryService s=fixture(); s.borrowBook("U001","B001"); assertThrows(IllegalStateException.class,()->s.borrowBook("T001","B001")); }
}
