package edu.bootcamp_sb.service_market.utill;



public class UsernameSanitization {

    public static String sanitizeUsername(String unSanitizeUsername){
        return unSanitizeUsername.replace("\\s+", "_");
    }

}
