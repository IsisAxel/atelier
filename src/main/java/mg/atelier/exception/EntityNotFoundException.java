package mg.atelier.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entityName)
    {
        super(entityName + " introuvable.");
    }
}
