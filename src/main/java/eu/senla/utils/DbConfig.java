package eu.senla.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:/application.properties")
public class DbConfig {

    @Value(value = "${driver}")
    private String Driver;
    @Value(value = "${url}")
    private String DbUrl;
    @Value(value = "${name}")
    private String Username;
    @Value(value = "${password}")
    private String Password;

    @Bean(destroyMethod = "close")
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(DbUrl, Username, Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
