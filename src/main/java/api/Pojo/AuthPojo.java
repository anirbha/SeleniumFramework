package api.Pojo;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String name) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String email) { this.body = body; }

}
