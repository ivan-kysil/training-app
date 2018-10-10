package ikysil.training.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ikysil.training.dao.OrderDao;
import ikysil.training.dao.impl.DbOrderDao;
import ikysil.training.dao.impl.InMemoryOrderDao;
import ikysil.training.dao.repo.OrderRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;


@Configuration
public class MainConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper(ApplicationContext applicationContext) {
        return new TrainingAppObjectMapperBuilder()
                .applicationContext(applicationContext)
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
    }

    @Bean
    public OrderDao orderDao(@Value("${db.host}") String dbHost, OrderRepo orderRepo) {
        if (StringUtils.isEmpty(dbHost)) {
            return new InMemoryOrderDao();
        } else {
            return new DbOrderDao(orderRepo);
        }

    }

    @Bean
    @ConditionalOnProperty("db.host")
    public DataSource dataSource(
            @Value("${db.host}") String dbHost,
            @Value("${db.port}") int dbPort,
            @Value("${db.name}") String dbName,
            @Value("${db.user}") String dbUser,
            @Value("${db.password}")  String dbPassword) {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();

        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://" + dbHost + ":" + dbPort + '/' + dbName);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }
}
