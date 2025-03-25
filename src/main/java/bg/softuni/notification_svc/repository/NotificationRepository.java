package bg.softuni.notification_svc.repository;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> getAllByTypeAndRecipientId(NotificationType type, UUID recipientId);

    List<Notification> getAllByRecipientId(UUID recipientId);
}
