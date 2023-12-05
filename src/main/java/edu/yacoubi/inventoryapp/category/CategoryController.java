package edu.yacoubi.inventoryapp.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repository;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = repository.findAll();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }
}
