package bg.softuni.notification_svc.web.dto;

import bg.softuni.notification_svc.model.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequest {

    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    private NotificationType type;

    @NotNull
    private UUID recipientId;

    @NotNull
    private UUID senderId;
}
