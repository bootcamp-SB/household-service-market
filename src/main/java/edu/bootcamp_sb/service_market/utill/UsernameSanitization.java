package edu.bootcamp_sb.service_market.utill;



public class UsernameSanitization {

    private UsernameSanitization() {
    }

    public static String sanitizeUsername(String unSanitizeUsername){
        return unSanitizeUsername.replace(" ", "_");
    }

}
