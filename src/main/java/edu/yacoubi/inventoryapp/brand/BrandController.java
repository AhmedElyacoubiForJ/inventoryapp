package edu.yacoubi.inventoryapp.brand;

import edu.yacoubi.inventoryapp.category.Category;
import edu.yacoubi.inventoryapp.category.CategoryRepository;
import edu.yacoubi.inventoryapp.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/brands/new")
    public String showNewBrandForm(Model model) {
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("listCategories", categories);
        model.addAttribute("brand", new Brand());
        return "brand_form";
    }

    @PostMapping("/brands/save")
    public String saveBrand(Brand brand) {
        brandRepository.save(brand);
        return "redirect:/"; //"redirect:/brands";
    }

    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrands", brands);
        return "brands";
    }
}
