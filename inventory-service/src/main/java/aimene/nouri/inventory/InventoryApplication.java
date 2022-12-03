package aimene.nouri.inventory;

import aimene.nouri.inventory.entities.Product;
import aimene.nouri.inventory.repositories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepo productRepo, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepo.save(new Product(null, "Iphone 14 Pro", 16000, 6));
            productRepo.save(new Product(null, "Asus Rog", 200000, 3));
            productRepo.save(new Product(null, "Samsung S22", 10000, 10));

            productRepo.findAll().forEach(System.out::println);
        };
    }
}
