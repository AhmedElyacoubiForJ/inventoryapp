package edu.yacoubi.inventoryapp.category;

import edu.yacoubi.inventoryapp.brand.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            length = 45,
            nullable = false,
            unique = true
    )
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}