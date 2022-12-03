package aimene.nouri.billingservice;

import aimene.nouri.billingservice.entities.Bill;
import aimene.nouri.billingservice.entities.ProductItem;
import aimene.nouri.billingservice.feign.CustomerRestClient;
import aimene.nouri.billingservice.feign.ProductItemRestClient;
import aimene.nouri.billingservice.models.Customer;
import aimene.nouri.billingservice.models.Product;
import aimene.nouri.billingservice.repositories.BillingRepo;
import aimene.nouri.billingservice.repositories.ProductItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillingRepo billingRepo, ProductItemRepo productItemRepo,
                            CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill1 = billingRepo.save(new Bill(null, new Date(), null, customer.getId(), null));
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
            productPagedModel.forEach(p -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1 + new Random().nextInt(100));
                productItem.setBill(bill1);
                productItem.setProductID(p.getId());
                productItemRepo.save(productItem);
            });
        };
    }
}
