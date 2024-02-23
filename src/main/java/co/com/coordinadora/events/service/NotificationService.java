package co.com.coordinadora.events.service;

import co.com.coordinadora.events.exceptions.NotFoundException;
import co.com.coordinadora.events.model.Notification;
import co.com.coordinadora.events.object.NotificationDto;
import co.com.coordinadora.events.repository.NotificationRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationDto save(NotificationDto notificationDto) {
        notificationDto.setNotificationId(Utilities.id());

        logger.info("save " + notificationDto.getNotificationId());

        notificationRepository.save(mapperToNotification(notificationDto));
        return notificationDto;
    }

    public NotificationDto update(NotificationDto notificationDto) {

        logger.info("update " + notificationDto.getNotificationId());

        notificationRepository.save(mapperToNotification(notificationDto));
        return notificationDto;
    }

    public NotificationDto findById(String id) {

        logger.info("findById " + id);

        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional
                .map(this::mapperToNotificationDto)
                .orElseThrow(() -> new NotFoundException("Información no encontrada"));
    }

    public Page<NotificationDto> findAll(Pageable pageable) {

        logger.info("findAll");

        return notificationRepository.findAll(pageable)
                .map(this::mapperToNotificationDto);
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
