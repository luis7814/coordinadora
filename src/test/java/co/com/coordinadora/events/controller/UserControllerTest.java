package co.com.coordinadora.events.controller;

import co.com.coordinadora.events.object.EventDto;
import co.com.coordinadora.events.object.UserDto;
import co.com.coordinadora.events.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testSaveUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("2");
        userDto.setEmail("john@example.com");
        userDto.setPassword("password");
        userDto.setName("John");
        when(userService.save(userDto)).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.save(userDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userDto, response.getBody());
    }

    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("2");
        userDto.setEmail("john@example.com");
        userDto.setPassword("password");
        userDto.setName("John");
        when(userService.save(userDto)).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.save(userDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userDto, response.getBody());
    }

    @Test
    void testFindById() throws Exception {
        UserDto userDto = new UserDto();
        given(userService.findById("1")).willReturn(userDto);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAll() throws Exception {
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();
        List<UserDto> userDtoList = Arrays.asList(userDto1, userDto2);
        given(userService.findAll()).willReturn(userDtoList);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(userDtoList.size()));
    }
}
