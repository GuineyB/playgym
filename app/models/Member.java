package models;

import play.db.jpa.Model;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;


@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String address;
    public String gender;
    public String email;
    public String password;
    public float height;
    public float startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstname, String lastname, String address, String gender, float height, float startingWeight, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.password = gender;
        this.height = height;
        this.startingWeight = startingWeight;
        this.email = email;
        this.password = password;
    }


    public static Member findByEmail(String email) {
        return find("email", email).first();
    }


    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }



}
