package edu.course.library;
import java.util.*;
public class InMemoryDatabase implements BorrowRepository {
 private final Map<String,User> users=new HashMap<>();
 private final Map<String,BookCopy> books=new HashMap<>();
 private final Map<String,BorrowRecord> records=new HashMap<>();
 public void saveUser(User u){users.put(u.getId(),u);} public User findUser(String id){return users.get(id);}
 public void saveBook(BookCopy b){books.put(b.getId(),b);} public BookCopy findBook(String id){return books.get(id);}
 public void saveBorrowRecord(BorrowRecord r){records.put(key(r.getUserId(),r.getBookCopyId()),r);}
 public BorrowRecord findBorrowRecord(String u,String b){return records.get(key(u,b));}
 public long countActiveBorrowings(String u){return records.values().stream().filter(r->r.getUserId().equals(u)&&!r.isReturned()).count();}
 private String key(String u,String b){return u+"::"+b;}
}
