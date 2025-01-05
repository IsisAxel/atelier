package mg.atelier.exception;

public class ForeignKeyException extends Exception{
    public ForeignKeyException(String entityName, String columnName, Object value) {
        super("L'entite " + entityName + " correspondant au champ de la cle etrangere '" + columnName + "'' = " + value +" est introuvable.");
    }
}
