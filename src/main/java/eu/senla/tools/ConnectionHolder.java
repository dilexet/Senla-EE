package eu.senla.tools;

import lombok.Getter;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class ConnectionHolder {
    private final Connection connection;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
