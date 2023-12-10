package edu.yacoubi.inventoryapp.product;

import edu.yacoubi.inventoryapp.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(
            length = 128,
            nullable = false,
            unique = true
    )
    private String name;

    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<ProductDetails> details = new ArrayList<>();

    public void addDetails(String name, String value) {
        details.add(new ProductDetails(name,value, this));
    }

    public void setDetail(Integer id, String name, String value) {
       this.details.add(new ProductDetails(id, name, value, this));
    }
}