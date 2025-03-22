package bg.softuni.notification_svc.web;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.service.NotificationService;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import bg.softuni.notification_svc.web.dto.NotificationResponse;
import bg.softuni.notification_svc.web.mapper.DtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        Notification notification = notificationService.sendNotification(request);
        NotificationResponse response = DtoMapper.toNotificationResponse(notification);
        return ResponseEntity.ok(response);
    }
}
