package not.amazon.echo.network;

/**
 * Exception Class to throw an exception when no speech is detected.
 * Created by Jacques-Antoine Portal on 01/03/2017.
 */
public class NoSpeechException extends Exception {
    public NoSpeechException() {
        super();
    }

    public NoSpeechException(String message) {
        super(message);
    }
}
