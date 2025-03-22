package bg.softuni.notification_svc.service;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.repository.NotificationRepository;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(NotificationRequest request) {

        Notification notification =  Notification.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .type(request.getType())
                .recipientId(request.getRecipientId())
                .senderId(request.getSenderId())
                .build();

        notificationRepository.save(notification);

        return notification;
    }
}
