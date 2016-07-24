package rs.jug.rx.resultset.dao.function;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import rx.Observable;
import rx.Observer;
import rx.functions.Func3;

public class ResultsetObserverFunction<T> implements Func3<ResultSet, Long, Observer<Observable<? extends T>>, ResultSet> {
	private ResultSetExtractor<T> extractor;
	
	public ResultsetObserverFunction(ResultSetExtractor<T> extractor){
		this.extractor = extractor;
	}
	
	public ResultSet call(ResultSet resultSet, Long requested, Observer<Observable<? extends T>> observer) {
		long retrievedResults = 0;
		try {
			while (resultSet.next() && retrievedResults <= requested) {
				observer.onNext(Observable.just(extractor.extractData(resultSet)));
				retrievedResults++;
			}
			return resultSet;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve results from database", e);
		}
	}

}
