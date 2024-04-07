package edu.yacoubi.inventoryapp.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repository;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = repository.findAll();
        model.addAttribute("listCategories", categories);
        return "category/list";
    }

    @GetMapping("/categories/new")
    public String showNewCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category) {
        repository.save(category);
        return "redirect:/categories";
    }
}
