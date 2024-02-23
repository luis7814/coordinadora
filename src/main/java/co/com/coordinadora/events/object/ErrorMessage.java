package co.com.coordinadora.events.object;

import lombok.Data;

@Data
public class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
