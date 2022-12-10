package aimene.nouri.billingservice.repositories;

import aimene.nouri.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BillingRepo extends JpaRepository<Bill, Long> {
    @RestResource(path = "/byCustomerID")
    List<Bill> findByCustomerID(@Param("customerID") Long customerID);
}
