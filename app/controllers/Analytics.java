package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;


public class Analytics extends Controller {

    public static double calculateBMI(Member member, Assessment assessment) {
        double bmi;
        if (member.height <= 0)
            return 0;
        else
            bmi = (member.startingWeight / (member.height * member.height));
        return toTwoDecimalPlaces(bmi);
    }

    public static String determineBMICategory(double bmiValue) {
        if (bmiValue >= 1 && bmiValue < 16) return "SEVERELY UNDERWEIGHT";
        else if (bmiValue >= 16 && bmiValue < 18.5) return "UNDERWEIGHT";
        else if (bmiValue >= 18.5 && bmiValue < 25) return "NORMAL";
        else if (bmiValue >= 25 && bmiValue < 30) return "OVERWEIGHT";
        else if (bmiValue >= 30 && bmiValue < 35) return "MODERATELY OBESE";
        else if (bmiValue >= 35) return "SEVERELY OBESE";
        return "invalid VALUES";
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        //    five feet = 5/ 3.2 = 1.5 meters    five feet = 60 inches
        double idealBodyWeight;

        double inInches = convertHeightMetresToInches(member, assessment);
        if (inInches <= 60) {            // five feet or less
            if (member.gender.equals("Male")) {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5;   // Female or unspecified
            }
        } else {
            if (member.gender.equals("Male")) {
                idealBodyWeight = 50 + ((inInches - 60) * 2.3);  // 50kg +2.3 for every inch over 5f

            } else {
                idealBodyWeight = 45.5 + ((inInches - 60) * 2.3); // F or unspecified 45.5kg +2.3 for every inch over 5f
            }
        }
        return
        ((idealBodyWeight <= (member.startingWeight + 2.0)) && (idealBodyWeight >= (member.startingWeight - 2.0)));

    }

    public static double convertWeightKGtoPounds(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(member.startingWeight * 2.20);
    }

    //Convert meters to Inches
    public static double convertHeightMetresToInches(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(member.height * 39.37);
    }

    //restrict to 2 decimal places
    private static double toTwoDecimalPlaces(double num) {
        return Math.round (num * 100) / 100.0;
    }
}
