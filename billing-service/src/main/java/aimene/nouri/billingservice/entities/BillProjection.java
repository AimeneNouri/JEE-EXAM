package aimene.nouri.billingservice.entities;

import aimene.nouri.billingservice.entities.Bill;
import aimene.nouri.billingservice.enums.BillStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullBill", types = Bill.class)
public interface BillProjection {
    Long getId();
    Date getBillingDate();
    BillStatus getStatus();
    Long getCustomerID();
}
