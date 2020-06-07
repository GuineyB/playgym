package models;

import play.db.jpa.Model;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;

@Entity
public class Trainer extends Model {
    public String trainerName;
    public String trainerEmail;
    public String trainerPassword;


    @OneToMany(cascade = CascadeType.ALL)
   public List<Member> member = new ArrayList<Member>();
//    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Trainer(String trainerName, String trainerEmail, String trainerPassword) {
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.trainerPassword = trainerPassword;
    }

    public static models.Trainer findByEmail(String trainerEmail) {
        return find("trainerEmail", trainerEmail).first();
    }

    public boolean checkTPassword(String trainerPassword) {
        return this.trainerPassword.equals(trainerPassword);
    }


}
