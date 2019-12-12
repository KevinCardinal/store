package be.wavenet.technocite.store.server.model;

import java.io.Serializable;

public class Market implements Serializable {

    private String id;
    private String name;
    private String locality;

    private Market() {}

    public Market(String id, String name, String locality) {
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
