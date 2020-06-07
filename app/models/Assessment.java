package models;
import java.util.List;
import play.db.jpa.Model;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;


@Entity
public class Assessment extends Model {
    public String date;
    public float weight;
    public float chest;
    public float thigh;
    public float upperarm;
    public float waist;
    public float hips;
    public String comment;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> member = new ArrayList<Member>();

    public Assessment(String date,float weight, float chest, float thigh, float upperarm, float waist, float hips, String comment) {
        this.date = date;
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperarm = upperarm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
    }
}
