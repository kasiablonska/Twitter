package pl.sda.twitter.exception;

public class IncorrectLoginPasswordException extends Exception{

    public IncorrectLoginPasswordException(String message){
        super(message);
    }
}
