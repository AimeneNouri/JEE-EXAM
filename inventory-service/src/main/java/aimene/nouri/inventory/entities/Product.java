package aimene.nouri.inventory.entities;

import javax.persistence.*;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Entity @ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double quantity;
}
