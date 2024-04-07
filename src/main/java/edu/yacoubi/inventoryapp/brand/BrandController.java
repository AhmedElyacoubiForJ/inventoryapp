package edu.yacoubi.inventoryapp.brand;

import edu.yacoubi.inventoryapp.category.Category;
import edu.yacoubi.inventoryapp.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "brand/form";
    }

    @PostMapping("/brands/save")
    public String saveBrand(Brand brand) {
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrands", brands);
        return "brand/list";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Integer id, Model model) {
        Brand brand = brandRepository.findById(id).get();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("brand", brand);
        model.addAttribute("listCategories", categories);
        return "brand/form";
    }
}
