package controllers;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import models.Assessment;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;



public class Trainer extends Controller
{

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> member = new ArrayList<Member>();
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public static void index() {
        Logger.info("Render Trainer Dashboard");
        List<Member> member = Member.findAll();  //  Create a listing of all member objects
        render ("trainer.html", member);
    }


}