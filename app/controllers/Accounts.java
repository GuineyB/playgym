package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
    public static void index() {
        render
                ("start.html");
    }

    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }


    public static void register(String firstname, String lastname, String address, String gender, float height, float startingWeight, String email, String password) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, address, gender, height, startingWeight, email, password);
        member.save();
        redirect("/");
    }


    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);
        Trainer trainer = Trainer.findByEmail(email);
        Member member = Member.findByEmail(email);
        boolean valid = false;
        if ((trainer != null) && (trainer.checkTPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            valid = true;
            redirect("/trainer");
        }
        else{
        if ((member != null) && (member.checkPassword(password))) {
                Logger.info("Authentication successful");
                session.put("logged_in_Memberid", member.id);
                valid = true;
                redirect("/memberdashboard");
            }
        }

        if  (valid == false){
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }

    public static void updateSettings(Member member) {
        Member loggedInMember = getLoggedInMember();
        loggedInMember.email = member.email;
        loggedInMember.firstname = member.firstname;
        loggedInMember.lastname = member.lastname;
        loggedInMember.password = member.password;
        loggedInMember.address = member.address;
        loggedInMember.gender = member.gender;
        loggedInMember.height = member.height;
        loggedInMember.startingWeight = member.startingWeight;
        loggedInMember.save();
        redirect
                ("/dashboard");

    }
}