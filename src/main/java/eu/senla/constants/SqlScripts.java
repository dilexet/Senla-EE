package eu.senla.constants;

public class SqlScripts {
    public static final String SQL_ADD = "INSERT INTO %s (%s, %s) VALUES (?,?)";
    public static final String SQL_FIND_BY_ID = "SELECT * FROM %s WHERE %s = ?";
    public static final String SQL_UPDATE = "UPDATE %s SET (%s, %s) = (?,?) WHERE %s = ?";
    public static final String SQL_REMOVE = "DELETE FROM %s WHERE %s = ?";

}
