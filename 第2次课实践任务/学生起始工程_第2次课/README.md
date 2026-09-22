# 第2次课学生起始工程

JDK 21。

运行：
- edu.course.library.App
- edu.course.library.BaselineChecks

本工程故意保留V0的职责和依赖问题。按任务单逐步修改，不要一次性重写。

## 第 2 次课重构说明
BookCopy 负责可借检查及借出、归还状态变更，User 负责教师 10 本、学生 5 本的借阅上限。
BorrowService 负责协调查询、规则检查、保存和借阅成功通知。
NotificationSender 接收用户和消息，SmsAdapter 封装短信地址、优先级及 LegacySmsClient 调用，原 SDK 未修改。
BorrowRepository 仅定义借阅流程所需的六个数据访问方法，由 InMemoryDatabase 实现，不包含 saveUser。
FakeNotificationSender 记录调用次数、最后接收者和消息；BaselineChecks 保留原基线并验证通知，不调用短信 SDK。
EmailNotificationSender 使用 User.getEmail()，通过控制台模拟发送，无额外邮件依赖。
App 分别装配短信和邮件实现，每个场景使用独立数据并检查正常借还状态。
加入 Email 时未修改 BorrowService、BookCopy、User 和 BorrowRepository 的业务逻辑。
