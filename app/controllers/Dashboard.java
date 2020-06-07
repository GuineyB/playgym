package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {


    public static void Allindex() {
        Logger.info("Rendering Admin");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = Assessment.findAll();
        render("dashboard.html", assessments);

    }

    public static void index() {
        Logger.info("Rendering Member Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        double bmi = Analytics.calculateBMI(member, assessments.get(0));
        boolean isIdealW = Analytics.isIdealBodyWeight(member, assessments.get(0));
        String bmiCat = Analytics.determineBMICategory(bmi);
        render("dashboard.html", assessments, member, bmi, isIdealW, bmiCat);
    }

/*
    public static void tindex() {
        Logger.info("Rendering Trainer Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        //  render("dashboard.html", members);
        render("trainerdashboard.html", members);

    }

 */
}
