package co.com.coordinadora.events.object;

import lombok.Data;

import java.io.Serializable;

@Data
public class NominatimResponse implements Serializable {

    private Long place_id;
    private String licence;
    private String osm_type;
    private Long osm_id;
    private String lat;
    private String lon;
    private String display_name;
    //private Address address;
}
