package edu.bootcamp_sb.service_market.exception;

public class ProviderHasBeenNotFoundException extends RuntimeException
{
    public ProviderHasBeenNotFoundException(String message) {
        super(message);
    }
}
