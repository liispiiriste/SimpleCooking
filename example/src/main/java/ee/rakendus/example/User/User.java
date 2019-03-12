package ee.rakendus.example.User;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;
    @Column(name ="email")
    private String email;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    protected User() {}

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, email='%s', username='%s', password='%s']",
                id, email, username, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
