package bg.softuni.notification_svc.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotificationResponse {

    private UUID id;

    private String title;

    private String body;

    private boolean readed;
}
