package co.com.coordinadora.events.service;

import co.com.coordinadora.events.model.User;
import co.com.coordinadora.events.object.UserDto;
import co.com.coordinadora.events.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        UserDto result = userService.save(userDtoData());
        Assertions.assertNotNull(result.getUserId());
    }

    @Test
    void testUpdate() {
        UserDto result = userService.update(userDtoData());
        Assertions.assertEquals(userDtoData().getUserId(), result.getUserId());
    }

    @Test
    void testFindById() {
        when(userRepository.findById("id")).thenReturn(Optional.of(userData()));
        UserDto result = userService.findById("id");
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<UserDto> result = userService.findAll();
        Assertions.assertNotNull(List.of(result));
    }

    private UserDto userDtoData() {
        UserDto userDto = new UserDto();
        userDto.setUserId("2");
        userDto.setEmail("email");
        userDto.setPassword("password");
        userDto.setName("name");

        return userDto;
    }

    private User userData() {
        User user = new User();
        user.setUserId("2");
        user.setEmail("email");
        user.setName("name");
        user.setPassword("password");

        return user;
    }
}
