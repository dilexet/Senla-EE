package eu.senla.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:/application.properties")
public class DbProperties {
    @Value(value = "${jdbc.driver}")
    private String Driver;
    @Value(value = "${jdbc.url}")
    private String DbUrl;
    @Value(value = "${jdbc.username}")
    private String Username;
    @Value(value = "${jdbc.password}")
    private String Password;
}
