package edu.course.library;
public class BookCopy {
 private final String id; private boolean available=true;
 public BookCopy(String id){this.id=id;}
 public String getId(){return id;} public boolean isAvailable(){return available;}
 public void setAvailable(boolean available){this.available=available;}
}
