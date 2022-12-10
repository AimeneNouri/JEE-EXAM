package aimene.nouri.billingservice.entities;

import aimene.nouri.billingservice.enums.BillStatus;
import aimene.nouri.billingservice.models.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @ToString @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private BillStatus status;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private long customerID;
    @Transient
    private Customer customer;

    public double getTotal(){
        return productItems.stream()
                .mapToDouble(ProductItem::getTotal).sum();
    }
}
