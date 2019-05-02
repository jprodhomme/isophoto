package co.simplon.titrepro.isophoto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IsophotoApplication implements CommandLineRunner {

	public static void main(String[] args)
			throws Exception, MalformedURLException, IOException, ClassNotFoundException, SQLException {
		SpringApplication.run(IsophotoApplication.class, args);
	}

	@Override
	public void run(String... args)
			throws Exception, MalformedURLException, IOException, ClassNotFoundException, SQLException {

	}
}
