package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.Notification;
import co.com.coordinadora.events.object.NotificationDto;
import co.com.coordinadora.events.repository.NotificationRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationDto save(NotificationDto notificationDto) {
        notificationDto.setNotificationId(Utilities.id());

        notificationRepository.save(mapperToNotification(notificationDto));
        return notificationDto;
    }

    public NotificationDto update(NotificationDto notificationDto) {
        notificationRepository.save(mapperToNotification(notificationDto));
        return notificationDto;
    }

    public NotificationDto findById(String id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.isPresent() ?
                mapperToNotificationDto(notificationOptional.get()) : null;
    }

    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(this::mapperToNotificationDto)
                .collect(Collectors.toList());
    }

    private NotificationDto mapperToNotificationDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationId(notification.getNotificationId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setNotificationDate(notification.getNotificationDate());
        notificationDto.setEventId(notification.getEventId());

        return notificationDto;
    }

    private Notification mapperToNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setNotificationId(notificationDto.getNotificationId());
        notification.setMessage(notificationDto.getMessage());
        notification.setNotificationDate(notificationDto.getNotificationDate());
        notification.setEventId(notificationDto.getEventId());

        return notification;
    }

}
