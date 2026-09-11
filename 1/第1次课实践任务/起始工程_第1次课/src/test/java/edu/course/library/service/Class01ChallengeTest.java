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
@Disabled("课堂中按任务单逐步启用")
class Class01ChallengeTest {

    @Test
    void studentCannotBorrowSixthBook() {
        // TODO: 创建 STUDENT + 6 本书；前5本成功，第6本应抛异常。
    }

    @Test
    void teacherCanBorrowTenButNotEleven() {
        // TODO: 创建 TEACHER + 11 本书；前10本成功，第11本应抛异常。
    }

    @Test
    void businessRuleTestShouldNotNeedRealSmsAfterRefactor() {
        // TODO: 单点重构后，用 Fake/Test Double 验证借阅业务，不依赖真实短信客户端。
    }
}
