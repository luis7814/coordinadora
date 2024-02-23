package co.com.coordinadora.events.object;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String name;
    private String email;
    private String password;
}
