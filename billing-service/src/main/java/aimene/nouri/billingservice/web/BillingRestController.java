package aimene.nouri.billingservice.web;

import aimene.nouri.billingservice.entities.Bill;
import aimene.nouri.billingservice.feign.CustomerRestClient;
import aimene.nouri.billingservice.feign.ProductItemRestClient;
import aimene.nouri.billingservice.models.Product;
import aimene.nouri.billingservice.repositories.BillingRepo;
import aimene.nouri.billingservice.repositories.ProductItemRepo;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingRestController {
    private BillingRepo billingRepo;
    private ProductItemRepo productItemRepo;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillingRepo billingRepo, ProductItemRepo productItemRepo, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billingRepo = billingRepo;
        this.productItemRepo = productItemRepo;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billingRepo.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(p -> {
            Product product = productItemRestClient.getProductById(p.getProductID());
            p.setProduct(product);
        });
        return bill;
    }

    @GetMapping(path = "/fullBill/")
    public List all(){
        return billingRepo.findAll();
    }
}
