package api.Pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthPojo {
    // Getters and setters

    private int id;

    private String title;

    private String body;

    public AuthPojo(String title, String body)
    {
        this.title=title;
        this.body=body;
    }

}
