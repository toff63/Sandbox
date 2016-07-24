package rs.jug.rx.resultset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceUtils;

import rs.jug.rx.resultset.dao.function.CallDatabaseFunction;
import rs.jug.rx.resultset.dao.function.CustomerResultSetExtractor;
import rs.jug.rx.resultset.dao.function.ReleaseConnectionFunction;
import rs.jug.rx.resultset.dao.function.ResultsetObserverFunction;
import rx.Observable;
import rx.observables.AsyncOnSubscribe;

public class ReactiveCustomerDAO implements CustomerDAO {

	private static final Logger log = LoggerFactory.getLogger(BlockingCustomerDAO.class);
	private JdbcTemplate jdbcTemplate;

	public ReactiveCustomerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Observable<Customer> doQueryDatabase(String sql, ResultSetExtractor<Customer> extractor) {
		Connection con = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			return Observable
					.create(AsyncOnSubscribe.createStateful(new CallDatabaseFunction(statement),
							new ResultsetObserverFunction<Customer>(extractor),
							new ReleaseConnectionFunction(con, jdbcTemplate.getDataSource())));
		} catch (SQLException e) {
			DataSourceUtils.releaseConnection(con, jdbcTemplate.getDataSource());
			throw new RuntimeException("Couldn't create statement", e);
		}
	}

	@Override
	public void queryDatabase() {
		log.info("Querying for customer records where first_name = 'Josh':");
		Observable<Customer> customers = 
				doQueryDatabase("SELECT id, first_name, last_name FROM customers WHERE first_name = 'Josh'", 
				new CustomerResultSetExtractor());
		customers.subscribe(customer -> log.info(customer.toString()));
	}

	@Override
	public void insertData() {
		Observable.from(Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")).subscribe(name -> {
			String[] firstAndLast = name.split(" ");
			log.info(String.format("Inserting customer record for %s %s", firstAndLast[0], firstAndLast[1]));
			jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", firstAndLast[0],
					firstAndLast[1]);
		});
	}

	@Override
	public void setUpDatabase() {
		log.info("Creating tables");
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	}

}
