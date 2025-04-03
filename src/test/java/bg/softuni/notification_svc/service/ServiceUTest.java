package bg.softuni.notification_svc.service;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceUTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void getAllNotifications_happyPath() {
        List<Notification> notifications = List.of(new Notification());
        when(notificationRepository.getAllByRecipientId(any(UUID.class))).thenReturn(notifications);

        assertEquals(notifications, notificationService.getAllNotificationByUser(UUID.randomUUID()));
    }

    @Test
    void throwExceptionWhenNotificationNotFound() {

        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());

        when(notificationRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->notificationService.setNotificationAsRead(notification.getId()));
        assertEquals("Notification with id " + notification.getId() + " not found", exception.getMessage());
    }
}
