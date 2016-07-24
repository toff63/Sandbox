package rs.jug.rx.resultset.dao.function;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import rx.functions.Action1;

public class ReleaseConnectionFunction implements Action1<ResultSet> {
	private Connection con;
	private DataSource datasource;
	
	public ReleaseConnectionFunction(Connection con, DataSource dataSource) {
		this.con = con;
		this.datasource = dataSource;
	}

	public void call(ResultSet t) {
		DataSourceUtils.releaseConnection(con, datasource);
	}
}
