package bg.softuni.notification_svc;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.model.NotificationType;
import bg.softuni.notification_svc.repository.NotificationRepository;
import bg.softuni.notification_svc.service.NotificationService;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class GetWarningStatusNotificationITest {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void getWarningStatusNotification() {

        UUID id = UUID.randomUUID();
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .title("Title")
                .body("Body")
                .type(NotificationType.WARNING)
                .recipientId(id)
                .build();
        notificationService.sendNotification(notificationRequest);


        List<Notification> notifications = notificationService.getWarningStatusNotification(id);

        assertEquals(1, notifications.size());
        assertEquals(NotificationType.WARNING, notifications.getFirst().getType());
    }
}
