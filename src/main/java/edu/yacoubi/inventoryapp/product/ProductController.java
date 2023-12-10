package edu.yacoubi.inventoryapp.product;

import edu.yacoubi.inventoryapp.category.Category;
import edu.yacoubi.inventoryapp.category.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    public String saveProduct(Product product, HttpServletRequest request) {
        String[] detailNames = request.getParameterValues("detailName");
        String[] detailValues = request.getParameterValues("detailValue");
        String[] detailIDs = request.getParameterValues("detailID");

        for (int i=0; i<detailNames.length; i++) {
            if (detailIDs != null && detailIDs.length > 0) {
                // edit product mode
                product.setDetail(
                        Integer.valueOf(detailIDs[i]),
                        detailNames[i],
                        detailValues[i]
                );
            } else {
                // new product mode
                product.addDetails(detailNames[i], detailValues[i]);
            }
        }
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
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).get();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", categories);
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}