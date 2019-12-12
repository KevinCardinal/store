package be.wavenet.technocite.store.server.dto;

import java.io.Serializable;

public class MarketDto implements Serializable {

    private String id;
    private String name;
    private String locality;

    private MarketDto() {}

    public MarketDto(String id, String name, String locality) {
        this.id = id;
        this.name = name;
        this.locality = locality;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }
}
