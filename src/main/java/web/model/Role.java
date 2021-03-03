package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles")
    //@JoinColumn(name = "user_id", nullable = false)
    private Set<User> users;

    public Role() {
    }

    public Role(Role role) {
        this.id = role.id;
        this.role = role.role;
        this.users = role.users;
    }

    public Role(Long id, String role){
        this.id = id;
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role(String role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}