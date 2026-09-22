package edu.course.library.service;

import edu.course.library.model.BookCopy;
import edu.course.library.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 第1次课挑战测试骨架。
 * 完成 Change Request 01 后，删除 @Disabled 并补齐 TODO。
 */

class Class01ChallengeTest {

    @Test
    void studentCannotBorrowSixthBook() {
        LibraryService s = new LibraryService();

        s.addUser(new User("S001", "Student", "STUDENT", "13800000001"));

        for (int i = 1; i <= 6; i++) {
            s.addBook(new BookCopy("B" + i, "Book " + i));
        }

        for (int i = 1; i <= 5; i++) {
            s.borrowBook("S001", "B" + i);
        }

        assertThrows(
            IllegalStateException.class,
            () -> s.borrowBook("S001", "B6")
        );
    }

    @Test
    void teacherCanBorrowTenButNotEleven() {
        LibraryService s = new LibraryService();

        s.addUser(new User("T001", "Teacher", "TEACHER", "13800000002"));

        for (int i = 1; i <= 11; i++) {
            s.addBook(new BookCopy("B" + i, "Book " + i));
        }

        for (int i = 1; i <= 10; i++) {
            s.borrowBook("T001", "B" + i);
        }

        assertThrows(
            IllegalStateException.class,
            () -> s.borrowBook("T001", "B11")
        );
    }

    @Test
    void businessRuleTestShouldNotNeedRealSmsAfterRefactor() {
    NotificationSender fakeNotification = (phone, message) -> {
        // 测试时不发送真实短信
    };

    LibraryService s = new LibraryService(fakeNotification);

    s.addUser(new User(
        "S001",
        "Student",
        "STUDENT",
        "13800000001"
    ));

    for (int i = 1; i <= 6; i++) {
        s.addBook(new BookCopy("B" + i, "Book " + i));
    }

    for (int i = 1; i <= 5; i++) {
        s.borrowBook("S001", "B" + i);
    }

    assertThrows(
        IllegalStateException.class,
        () -> s.borrowBook("S001", "B6")
    );
    }

    @Test
    void canUseEmailNotification() {
    NotificationSender email =
        new edu.course.library.external.EmailNotificationSender();

    LibraryService s = new LibraryService(email);

    s.addUser(new User(
        "S002",
        "Email Student",
        "STUDENT",
        "student@example.com"
    ));

    s.addBook(new BookCopy("E001", "Email Book"));

    s.borrowBook("S002", "E001");

    assertFalse(s.findBook("E001").isAvailable());
    }
}
