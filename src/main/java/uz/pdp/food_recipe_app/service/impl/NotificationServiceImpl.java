package uz.pdp.food_recipe_app.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.NotificationDto;
import uz.pdp.food_recipe_app.model.entity.Notification;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.NotificationRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.FollowService;
import uz.pdp.food_recipe_app.service.abstractions.NotificationService;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final FollowService followService;
    private final UserRepository userRepository;

    @Override
    public List<NotificationDto> getAllNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .peek(notification -> notification.setIsRead(true))
                .sorted(Comparator.comparing(Notification::getCreatedAt))
                .map(notification -> new NotificationDto(
                        notification.getMessage(),
                        notification.getUser().getName(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public List<NotificationDto> getReadNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .filter(Notification::getIsRead)
                .peek(notification -> notification.setIsRead(true))
                .sorted(Comparator.comparing(Notification::getCreatedAt))
                .map(notification -> new NotificationDto(
                        notification.getMessage(),
                        notification.getUser().getName(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public List<NotificationDto> getUnReadNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .filter(notification -> !notification.getIsRead())
                .sorted(Comparator.comparing(Notification::getCreatedAt))
                .map(notification -> new NotificationDto(
                        notification.getMessage(),
                        notification.getUser().getName(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public void createAndSendToUsers(FoodAddDto foodAddDto) {
        List<User> followers = followService.findFollowers(foodAddDto.getUserId());
        User receiver = userRepository.findById(foodAddDto.getUserId()).orElseThrow();
        for (User follower : followers) {
            Notification notification = new Notification();
            notification.setUser(follower);
            notification.setReceiverUser(receiver);
            notification.setMessage("There was created a new food called " + foodAddDto.getName());
            notification.setIsRead(false);
            notificationRepository.save(notification);
        }
    }
}
