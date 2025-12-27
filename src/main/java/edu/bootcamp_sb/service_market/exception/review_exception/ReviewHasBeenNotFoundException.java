package edu.bootcamp_sb.service_market.exception.review_exception;

public class ReviewHasBeenNotFoundException extends RuntimeException{
    public ReviewHasBeenNotFoundException(String message){
        super(message);
    }
}
