package javapro.spring._1;

import javapro.spring._1.models.AppUser;
import javapro.spring._1.models.Car;
import javapro.spring._1.models.Owner;
import javapro.spring._1.repositories.AppUserRepository;
import javapro.spring._1.repositories.CarRepository;
import javapro.spring._1.repositories.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository appUserRepository;
	public Application(
			CarRepository repository,
			OwnerRepository ownerRepository,
			AppUserRepository appUserRepository
	) {
		this.carRepository = repository;
		this.ownerRepository = ownerRepository;
		this.appUserRepository = appUserRepository;
	}

	public static void main( String[] args )
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add owner objects and save these to db
		Owner owner1 = new Owner("John" , "Johnson");
		Owner owner2 = new Owner("Mary" , "Robinson");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
		carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
		carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));
		// Fetch all cars and log to console
		for (Car car : carRepository.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}

		/// Bcrypt Format
		/// $2<a/b/x/y>$[cost]$[22 character salt][31 character hash]
		///
		// Username: user, password: user
		appUserRepository.save(new AppUser("user",
				"$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
		// Username: admin, password: admin
		appUserRepository.save(new AppUser("admin",
				"$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
	}
}
