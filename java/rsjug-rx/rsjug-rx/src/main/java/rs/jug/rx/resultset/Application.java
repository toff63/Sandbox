package rs.jug.rx.resultset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.jug.rx.resultset.dao.CustomerDAO;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired CustomerDAO customerDao;

    @Override
    public void run(String... strings) throws Exception {
    	customerDao.setUpDatabase();
    	customerDao.insertData();
    	customerDao.queryDatabase();
    }



}
