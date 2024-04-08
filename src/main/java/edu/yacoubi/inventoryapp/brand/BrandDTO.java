package edu.yacoubi.inventoryapp.brand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO {

    private Integer id;
    @NotNull
    @Size(max = 45)
    private String name;
}
