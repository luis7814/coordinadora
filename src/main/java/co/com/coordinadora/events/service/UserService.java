package co.com.coordinadora.events.service;

import co.com.coordinadora.events.exceptions.NotFoundException;
import co.com.coordinadora.events.model.User;
import co.com.coordinadora.events.object.UserDto;
import co.com.coordinadora.events.repository.UserRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        userDto.setUserId(Utilities.id());

        logger.info("save " + userDto.getUserId());

        userRepository.save(mapperToUser(userDto));
        return userDto;
    }

    public UserDto update(UserDto userDto) {

        logger.info("update " + userDto.getUserId());

        userRepository.save(mapperToUser(userDto));
        return userDto;
    }

    public UserDto findById(String id) {

        logger.info("findById " + id);

        Optional<User> userOptional = userRepository.findById(id);
        return userOptional
                .map(this::mapperToUserDto)
                .orElseThrow(() -> new NotFoundException("Información no encontrada"));
    }

    public Page<UserDto> findAll(Pageable pageable) {

        logger.info("findAll");

        return userRepository.findAll(pageable)
                .map(this::mapperToUserDto);
    }

    private UserDto mapperToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());

        return userDto;
    }

    private User mapperToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setPassword(user.getPassword());

        return user;
    }

}
