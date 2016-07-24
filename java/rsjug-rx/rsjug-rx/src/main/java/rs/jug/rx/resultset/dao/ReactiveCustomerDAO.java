package rs.jug.rx.resultset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import rx.Observable;
import rx.Observer;
import rx.functions.Func3;
import rx.functions.Action1;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.observables.AsyncOnSubscribe;

public class ReactiveCustomerDAO implements CustomerDAO {

	private static final Logger log = LoggerFactory.getLogger(BlockingCustomerDAO.class);
	private JdbcTemplate jdbcTemplate;

	public ReactiveCustomerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	


	@Override
	public void queryDatabase() {
		log.info("Querying for customer records where first_name = 'Josh':");
		Connection con = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		try {
			PreparedStatement statement = con
					.prepareStatement("SELECT id, first_name, last_name FROM customers WHERE first_name = 'Josh'");
			Observable.create(AsyncOnSubscribe.createStateful(new Func0<ResultSet>() {
				public ResultSet call() {
					try {
						return statement.executeQuery();
					} catch (SQLException e) {
						throw new RuntimeException("Failed to query database to find Josh", e);
					}
				}
			}, new Func3<ResultSet, Long, Observer<Observable<? extends Customer>>, ResultSet>() {
				public ResultSet call(ResultSet resultSet, Long requested, Observer<Observable<? extends Customer>> observer) {
					long retrievedResults = 0;
					try {
						while (resultSet.next() && retrievedResults <= requested) {
							observer.onNext(Observable.just(new Customer(resultSet.getLong("id"),
									resultSet.getString("first_name"), resultSet.getString("last_name"))));
						}
						return resultSet;
					} catch (SQLException e) {
						throw new RuntimeException("Failed to retrieve results from database", e);
					}
				}
			}, new Action1<ResultSet>() {
				public void call(ResultSet t) {
					DataSourceUtils.releaseConnection(con, jdbcTemplate.getDataSource());
				}
			})).subscribe(customer -> log.info(customer.toString()));
		} catch (SQLException e) {
			DataSourceUtils.releaseConnection(con, jdbcTemplate.getDataSource());
			throw new RuntimeException("Couldn't create statement", e);
		}
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
