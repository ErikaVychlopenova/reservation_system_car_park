package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;

//    @OneToMany(mappedBy = "owner")
//    private List<Car> carsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Car> getCarsList() {
//        return carsList;
//    }
//
//    public void setCarsList(Car car) {
//        this.carsList.add(car);
//    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", email='" + email + '\'' +
//                "}\n";
//    }
}
