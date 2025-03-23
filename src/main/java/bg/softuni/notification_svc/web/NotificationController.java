package bg.softuni.notification_svc.web;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.service.NotificationService;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import bg.softuni.notification_svc.web.dto.NotificationResponse;
import bg.softuni.notification_svc.web.mapper.DtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        Notification notification = notificationService.sendNotification(request);
        NotificationResponse response = DtoMapper.toNotificationResponse(notification);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/read/{id}")
    public ResponseEntity<Void> readNotification(@PathVariable UUID id) {
        notificationService.setNotificationAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/error-status/{id}")
    public ResponseEntity<List<Notification>> errorStatus(@PathVariable UUID id) {
        List<Notification> errorStatusNotification = notificationService.getErrorStatusNotification(id);
        return ResponseEntity.ok(errorStatusNotification);
    }

}
