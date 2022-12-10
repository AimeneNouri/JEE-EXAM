package aimene.nouri.billingservice;

import aimene.nouri.billingservice.entities.Bill;
import aimene.nouri.billingservice.entities.ProductItem;
import aimene.nouri.billingservice.enums.BillStatus;
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
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            List<Customer> customers = customerRestClient.getAllCustomers().getContent().stream().collect(Collectors.toList());
            List<Product> products = productItemRestClient.pageProducts().getContent().stream().collect(Collectors.toList());

            Long customerID = 1L;
            Random random = new Random();
            Customer customer = customerRestClient.getCustomerById(customerID);
            for (int i = 0; i < 10; i++) {
                Bill bill = Bill.builder()
                        .customerID(customers.get(random.nextInt(customers.size())).getId())
                        .status(Math.random()>0.5? BillStatus.CREATED:BillStatus.PENDING)
                        .billingDate(new Date())
                        .build();
                Bill savedBill = billingRepo.save(bill);

                for (Product product : products) {
                    if (Math.random() > 0.70) {
                        ProductItem productItem = ProductItem.builder()
                                .bill(savedBill)
                                .price(product.getPrice())
                                .quantity(2 + random.nextInt(20))
                                .productID(product.getId())
                                .discount(Math.random())
                                .build();

                        productItemRepo.save(productItem);
                    }
                }
            }
        };
    }
}
