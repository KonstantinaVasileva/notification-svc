package bg.softuni.notification_svc.web;

import bg.softuni.notification_svc.model.Notification;
import bg.softuni.notification_svc.model.NotificationType;
import bg.softuni.notification_svc.service.NotificationService;
import bg.softuni.notification_svc.web.dto.NotificationRequest;
import bg.softuni.notification_svc.web.dto.NotificationResponse;
import bg.softuni.notification_svc.web.mapper.DtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
public class NotificationControllerApiTest {

    @MockitoBean
    private NotificationService notificationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sendNotification_happyPath() throws Exception {
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .title("Title")
                .body("Body")
                .type(NotificationType.INFO)
                .recipientId(UUID.randomUUID())
                .build();

        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .title(notificationRequest.getTitle())
                .body(notificationRequest.getBody())
                .type(notificationRequest.getType())
                .recipientId(notificationRequest.getRecipientId())
                .build();


        when(notificationService.sendNotification(any(NotificationRequest.class))).thenReturn(notification);
        NotificationResponse response = DtoMapper.toNotificationResponse(notification);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/notifications/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(response))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").isNotEmpty())
                .andExpect(jsonPath("body").isNotEmpty());
    }

    @Test
    void successfullyReadNotification() throws Exception {
        NotificationRequest dto = NotificationRequest.builder()
                .title("Title")
                .body("Body")
                .type(NotificationType.INFO)
                .recipientId(UUID.randomUUID())
                .build();

        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .title(dto.getTitle())
                .body(dto.getBody())
                .type(NotificationType.INFO)
                .recipientId(dto.getRecipientId())
                .build();

        when(notificationService.setNotificationAsRead(any(UUID.class))).thenReturn(notification);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/notifications/read/{id}", notification.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(dto))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").isNotEmpty())
                .andExpect(jsonPath("body").isNotEmpty())
                .andExpect(jsonPath("type").isNotEmpty())
                .andExpect(jsonPath("recipientId").isNotEmpty());
    }

}
