package bg.softuni.notification_svc.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    private String title;

    private String body;
}
