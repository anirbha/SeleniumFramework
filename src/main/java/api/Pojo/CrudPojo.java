package api.Pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CrudPojo {

    private int id;

    private String name;
    private String email;

    public CrudPojo() {}

    public CrudPojo(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // Getters and setters


}
