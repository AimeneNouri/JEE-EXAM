package aimene.nouri.customerservice;

import aimene.nouri.customerservice.entities.Customer;
import aimene.nouri.customerservice.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepo customerRepo, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepo.save(new Customer(null, "Aimene Nouri", "aimenenouri@gmail.com"));
            customerRepo.save(new Customer(null, "Hassan Nouri", "hassannouri@gmail.com"));
            customerRepo.save(new Customer(null, "Ikram Nouri", "ikramnouri@gmail.com"));

            customerRepo.findAll().forEach(System.out::println);
        };
    }
}
