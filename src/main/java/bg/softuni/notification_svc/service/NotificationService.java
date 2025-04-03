package bg.softuni.notification_svc.service;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.model.NotificationType;
import bg.softuni.notification_svc.repository.NotificationRepository;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    @PersistenceContext
    private EntityManager entityManager;

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
                .createAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        return notification;
    }

    @Transactional
    public Notification setNotificationAsRead(UUID id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()) {
                    throw new EntityNotFoundException("Notification with id " + id + " not found");
        }

        Notification notification = byId.get();
        notification.setReaded(true);
        notificationRepository.save(notification);

        return notification;
    }

    public List<Notification> getWarningStatusNotification(UUID id) {
        return notificationRepository.getAllByTypeAndRecipientId(NotificationType.WARNING, id);
    }

    public List<Notification> getAllNotificationByUser(UUID id) {
        return notificationRepository.getAllByRecipientId(id);
    }

}
