package mg.atelier.exception;

public class NotNullException extends Exception {
    public NotNullException(String columnName) {
        super("Le champ "+ columnName +" est invalide ou vide.");
    }
}