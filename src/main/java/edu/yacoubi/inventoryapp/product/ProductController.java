package edu.yacoubi.inventoryapp.product;

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
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("listCategories", categories);
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("listProducts", products);
        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(
            @PathVariable("id") Integer id,
            Model model) {

        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("listCategories", categories);

        return "product_form";
    }
}
