package almaszy.pokerapp.Model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "name cannot be empty!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "last name cannot be empty!")
    @Column(name = "lastname")
    private String lastname;

    @Digits(integer = 9, fraction = 0, message = "Wrong phone number format!")
    @Column(name = "telnumber")
    private String telnumber;

    @Email
    @Pattern(regexp=".+@.+\\..+", message = "Wrong email address format!")
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Pattern(regexp="[ a-zA-Z]+", message = "Wrong city format!")
    @Column(name = "city")
    private String city;

    @Pattern(regexp="[0-9]{2}-[0-9]{3}", message = "Wrong postalcode format!")
    @Column(name = "postalcode")
    private String postalcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
}