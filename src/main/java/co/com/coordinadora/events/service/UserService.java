package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.User;
import co.com.coordinadora.events.object.UserDto;
import co.com.coordinadora.events.repository.UserRepository;
import co.com.coordinadora.events.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        userDto.setUserId(Utilities.id());

        userRepository.save(mapperToUser(userDto));
        return userDto;
    }

    public UserDto update(UserDto userDto) {
        userRepository.save(mapperToUser(userDto));
        return userDto;
    }

    public UserDto findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.isPresent() ?
                mapperToUserDto(userOptional.get()) : null;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(this::mapperToUserDto)
                .collect(Collectors.toList());
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
