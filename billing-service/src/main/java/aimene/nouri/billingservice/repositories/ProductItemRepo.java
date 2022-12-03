package aimene.nouri.billingservice.repositories;

import aimene.nouri.billingservice.entities.ProductItem;
import aimene.nouri.billingservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepo extends JpaRepository<ProductItem, Long> {
    Collection<ProductItem> findByBillId(Long id);
}
