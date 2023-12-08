package edu.yacoubi.inventoryapp.brand;

import edu.yacoubi.inventoryapp.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(
            length = 45,
            nullable = false,
            unique = true
    )
    private String name;

    @OneToMany // (mappedBy = "brand", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private List<Category> categories = new ArrayList<>();
}
