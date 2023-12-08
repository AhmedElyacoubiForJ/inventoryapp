package edu.yacoubi.inventoryapp.category;

import edu.yacoubi.inventoryapp.brand.Brand;
import jakarta.persistence.*;
import lombok.*;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
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

    @Override
    public String toString() {
        return name;
    }
}