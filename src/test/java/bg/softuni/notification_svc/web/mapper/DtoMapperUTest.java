package bg.softuni.notification_svc.web.mapper;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.web.dto.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DtoMapperUTest {

    @Test
    void successfullyMappingNotificationToNotificationResponse() {
        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .title("Title")
                .body("Body")
                .readed(true)
                .build();

        NotificationResponse notificationResponse =DtoMapper.toNotificationResponse(notification);

        assertEquals(notification.getId(), notificationResponse.getId());
        assertEquals(notification.getTitle(), notificationResponse.getTitle());
        assertEquals(notification.getBody(), notificationResponse.getBody());
        assertEquals(notification.isReaded(), notificationResponse.isReaded());

    }
}
