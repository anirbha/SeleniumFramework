package api.Pojo;

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



    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
