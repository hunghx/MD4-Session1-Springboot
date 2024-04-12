package ra.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.example.demo.model.entity.Product;
import ra.example.demo.service.IProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    @GetMapping("/list")
    public  String list(Model model){
        model.addAttribute("products",productService.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("product",new Product());
        return "add";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute Product product ,@RequestParam MultipartFile file, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("product",product);
            return "add";
        }
        productService.save(product,file);
        return "redirect:/products/list";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute Product product, @RequestParam MultipartFile file){
        productService.save(product,file);
        return "redirect:/products/list";
    }
    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/products/list";
    }
    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable Long id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "edit";
    }
}
