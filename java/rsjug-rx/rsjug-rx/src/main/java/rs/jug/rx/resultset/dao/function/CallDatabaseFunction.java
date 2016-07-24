package rs.jug.rx.resultset.dao.function;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rx.functions.Func0;

public class CallDatabaseFunction implements Func0<ResultSet>{
	private PreparedStatement statement;
	
	public CallDatabaseFunction(PreparedStatement statement){
		this.statement = statement;
	}
	public ResultSet call() {
		try {
			return statement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to query database to find Josh", e);
		}
	}
}
