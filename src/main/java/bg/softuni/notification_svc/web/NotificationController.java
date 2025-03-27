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

    @GetMapping("/read/{id}")
    public ResponseEntity<Notification> readNotification(@PathVariable UUID id) {
        Notification notification = notificationService.setNotificationAsRead(id);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/error-status/{id}")
    public ResponseEntity<List<NotificationResponse>> errorStatus(@PathVariable UUID id) {
        List<Notification> errorStatusNotification = notificationService.getErrorStatusNotification(id);
        List<NotificationResponse> errorNotificationResponseList = errorStatusNotification.stream().map(DtoMapper::toNotificationResponse).toList();
        return ResponseEntity.ok(errorNotificationResponseList);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<NotificationResponse>> allNotifications(@PathVariable UUID id) {
        List<Notification> allNotification = notificationService.getAllNotificationByUser(id);
        List<NotificationResponse> allNotificationResponseList = allNotification.stream().map(DtoMapper::toNotificationResponse).toList();
        return ResponseEntity.ok(allNotificationResponseList);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Notification> getNotification(@PathVariable UUID id) {
//        Notification notification = notificationService.getNotification(id);
//        DtoMapper.toNotificationResponse(notification);
//        return ResponseEntity.ok(notification);
//    }
}
