package controllers;

import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

public class Admin extends Controller {

    public static void index() {
        Logger.info("Rendering ");
        List<Assessment> assessment = Assessment.findAll();
        render("admin.html", assessment);
    }
}
