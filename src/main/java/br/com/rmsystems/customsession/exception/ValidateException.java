package br.com.rmsystems.customsession.exception;

/**
 * The validate exception
 */
public class ValidateException extends RuntimeException {

    /**
     *
     * @param message the error message
     */
    public ValidateException(String message) {
        super(message);
    }
}
