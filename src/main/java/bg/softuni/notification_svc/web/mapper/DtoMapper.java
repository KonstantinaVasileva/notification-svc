package bg.softuni.notification_svc.web.mapper;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.web.dto.NotificationResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static NotificationResponse toNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .title(notification.getTitle())
                .body(notification.getBody())
                .build();
    }
}
