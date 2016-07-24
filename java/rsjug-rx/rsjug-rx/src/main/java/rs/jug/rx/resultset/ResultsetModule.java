package rs.jug.rx.resultset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import rs.jug.rx.resultset.dao.BlockingCustomerDAO;
import rs.jug.rx.resultset.dao.CustomerDAO;
import rs.jug.rx.resultset.dao.ReactiveCustomerDAO;

@Configuration
public class ResultsetModule {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
	@Bean
	public CustomerDAO customerDao(){
		return new ReactiveCustomerDAO(jdbcTemplate);
	}
}
