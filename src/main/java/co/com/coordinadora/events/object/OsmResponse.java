package co.com.coordinadora.events.object;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OsmResponse implements Serializable {

    private List<OsmElement> elements;

}
