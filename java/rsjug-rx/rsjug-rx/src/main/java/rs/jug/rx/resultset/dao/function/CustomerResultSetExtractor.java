package rs.jug.rx.resultset.dao.function;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import rs.jug.rx.resultset.dao.Customer;

public class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {

	@Override
	public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
		return new Customer(rs.getLong("id"),
				rs.getString("first_name"), rs.getString("last_name"));
	}
}
