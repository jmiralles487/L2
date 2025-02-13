package pe.gob.mtpe.rios.infraestructura.contexto;

import oracle.jdbc.OracleDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SrisLossSysContext {

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(OracleDriver.class.getName());
        dataSource.setUrl("jdbc:oracle:thin:@192.168.105.121:1539/mtpe_desa");
        dataSource.setUsername("SRISLOSSYS");
        dataSource.setPassword("SRISLOSSYS");
        return dataSource;
    }

    // Resto del código...
}