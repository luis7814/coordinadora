package co.com.coordinadora.events.object;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class OsmElement implements Serializable {

    private String type;
    private long id;
    private double lat;
    private double lon;
    private Map<String, String> tags;
}
