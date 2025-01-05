package mg.atelier.exception;

public class UniqueException extends Exception {
    public UniqueException(String columnName , Object value) {
        super("La valeur '"+ value +"' du champ '"+ columnName +"' est deja presente.");
    }
}
