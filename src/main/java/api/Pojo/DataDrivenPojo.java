package api.Pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DataDrivenPojo {

        private String name;
        private String username;
        private String email;
        private int id;

        // Default constructor
        public DataDrivenPojo() {}

        // Parameterized constructor
        public DataDrivenPojo(String name, String username, String email) {
            this.name = name;
            this.username = username;
            this.email = email;
        }

}


