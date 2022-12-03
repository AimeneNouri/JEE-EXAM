package aimene.nouri.billingservice.repositories;

import aimene.nouri.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillingRepo extends JpaRepository<Bill, Long> {
}
