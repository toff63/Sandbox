package rs.jug.rx.resultset;
import java.sql.Connection;
import java.sql.DriverManager;


public class ReactiveResultSet {
	
	public static void main(String[] args) throws Exception{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
		conn.close();
	}
	
	
    public boolean someLibraryMethod() {
        return true;
    }
}
