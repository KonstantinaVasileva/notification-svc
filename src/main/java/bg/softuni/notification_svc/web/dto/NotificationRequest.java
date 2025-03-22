package bg.softuni.notification_svc.web.dto;

import bg.softuni.notification_svc.model.NotificationType;
import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequest {
    private String title;
    private String body;
    private NotificationType type;
    private UUID recipientId;
    private UUID senderId;
}
