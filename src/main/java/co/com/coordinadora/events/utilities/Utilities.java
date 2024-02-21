package co.com.coordinadora.events.utilities;

import java.util.UUID;

public class Utilities {

    public static String id() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);
    }
}
