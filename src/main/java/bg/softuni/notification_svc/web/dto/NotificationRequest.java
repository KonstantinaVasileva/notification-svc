package bg.softuni.notification_svc.web.dto;

import bg.softuni.notification_svc.model.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotificationRequest {

    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    private NotificationType type;

    @NotNull
    private UUID recipientId;
}
