package controllers;
import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class GymCtrl extends Controller {

    public static void index(Long id) {
            Member member = Member.findById(id);
            Logger.info ("Member id = " + id);
            render("dashboard.html", member);
     }


    public static void assessmentIndex(Long id) {
        //Used to listing of members for the trainer
        Member member = Member.findById(id);
        Logger.info("Member id = " + id);
        render("listassessments.html", member);
    }

    public static void trainerIndex(Long id) {
        //Used to listing of members for the trainer
        Member member = Member.findById(id);
        Logger.info("Member id = " + id);
        render("trainer.html", member);
    }


    public static void checkMember() {
        Member member = Accounts.getLoggedInMember();
        Logger.info("Logged in Member id = " + member.id);
        render("memberdashboard.html", member);
    }


    public static void updateMember(String firstname, String lastname, String address, String gender, float height, float startingWeight, String email, String password) {
        Member member = Accounts.getLoggedInMember();
        Logger.info("Updating existing member " + member.email);

        member.firstname = firstname;
        member.lastname = lastname;
        member.address = address;
        member.gender = gender;
        member.height = height;
        member.startingWeight = startingWeight;
        member.email = email;
        member.password = password;
        member.save();
        redirect("/member");
    }

    public static void updateComment(Long id, Long assessmentid, String comment) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.comment = comment;
        assessment.save();
        member.save();
        Logger.info("Updating assessment comment " + assessmentid);
        //redirect("/assessments/");
        render("listassessments.html", member);
    }


    public static void calculatedBMI(){
        Logger.info("BMI Calc");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        double bmi = Analytics.calculateBMI(member, assessments.get(0));
        boolean isIdealBodyWeight = Analytics.isIdealBodyWeight(member, assessments.get(0));
        String determineBMICategory = Analytics.determineBMICategory(bmi);
       render("dashboard.html", assessments, member, bmi, isIdealBodyWeight, determineBMICategory);
     //   render("dashboard.html", assessments, member);
    }

    public static void addAssessment(String date,float weight, float chest, float thigh,float upperarm, float waist, float hips,String comment)
    {
        Logger.info("Adding Assessment");
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(date,weight, chest, thigh, upperarm, waist, hips,comment);
        //    MemberReport memberReports = Analytics.generateMemberReports(member);
        //     assessment.trend = memberReports.trend;
        member.assessments.add(assessment);
        member.save();
        redirect("/dashboard");
    }


    public static void deleteAssessment(Long assessmentid) {
    //  Member member = Member.findById(memberid);
        Assessment assessment = Assessment.findById(assessmentid);
        //     member.assessments.remove(assessment);
        //    member.save();
        assessment.delete();
        redirect("/dashboard/" + assessmentid);
    }


    public static void deleteMember(Long id) {
        Logger.info("Deleting a Member");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        member.assessments.remove(assessments);
        member.delete();
      //  redirect("/trainer/", member,assessments);
        redirect("/trainer/" +member +assessments);
    }
}