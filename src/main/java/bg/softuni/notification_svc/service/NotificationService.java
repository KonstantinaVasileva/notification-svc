package bg.softuni.notification_svc.service;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.model.NotificationType;
import bg.softuni.notification_svc.repository.NotificationRepository;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(NotificationRequest request) {

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .type(request.getType())
                .recipientId(request.getRecipientId())
                .senderId(request.getSenderId())
                .build();

        notificationRepository.save(notification);

        return notification;
    }

    public void setNotificationAsRead(UUID id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("Notification with id " + id + " not found");
        }

        Notification notification = byId.get();
        notification.setReaded(true);
        notificationRepository.save(notification);
    }

    public List<Notification> getErrorStatusNotification(UUID id) {
        return notificationRepository.getAllByTypeAndRecipientId(NotificationType.ERROR, id);
    }
}
