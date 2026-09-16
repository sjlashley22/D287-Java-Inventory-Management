package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import com.example.demo.domain.InhousePart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 *
 *
 */

@Controller
public class MainScreenControllerr {
   // private final PartRepository partRepository;
   // private final ProductRepository productRepository;'

    private PartService partService;
    private ProductService productService;

    private List<Part> theParts;
    private List<Product> theProducts;

 /*   public MainScreenControllerr(PartRepository partRepository, ProductRepository productRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
    }*/

    public MainScreenControllerr(PartService partService,ProductService productService){
        this.partService=partService;
        this.productService=productService;
    }
    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword){
        //add to the sprig model
        initializeSampleInventoryIfEmpty();
        List<Part> partList=partService.listAll(partkeyword);
        theModel.addAttribute("parts",partList);
        theModel.addAttribute("partkeyword",partkeyword);
    //    theModel.addAttribute("products",productService.findAll());
        List<Product> productList=productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword",productkeyword);
        return "mainscreen";
    }
    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
    private void initializeSampleInventoryIfEmpty() {

        List<Part> existingParts = partService.listAll(null);
        List<Product> existingProducts = productService.listAll(null);

        if (!existingParts.isEmpty() || !existingProducts.isEmpty()) {
            return;
        }

        Set<String> ingredientNames = new HashSet<>();

        //INGREDIENTS
        addIngredient("Beef Patty", 1.00, 50, ingredientNames);
        addIngredient("Sesame Bun", 0.50, 50, ingredientNames);
        addIngredient("Cheddar Cheese Slice", 0.50, 50, ingredientNames);
        addIngredient("Lettuce Leaf", 0.10, 50, ingredientNames);

        //multi-pack
        addIngredient("Beef Patty", 1.00, 50, ingredientNames);

        //BURGERS
        addProduct("Classic Burger", 5.00, 50);
        addProduct("Cheeseburger", 6.00, 50);
        addProduct("Double Patty Burger", 7.00, 50);
        addProduct("Bacon Burger", 8.00, 50);
        addProduct("Salmon Burger", 7.00, 50);
    }
    private void addIngredient(String baseName,
                               double price,
                               int inv,
                               Set<String> ingredientNames) {

        String finalName = baseName;

        if (!ingredientNames.add(baseName)) {
            finalName = baseName + " (Multi)";
            ingredientNames.add(finalName);
        }

        InhousePart part = new InhousePart();
        part.setName(finalName);
        part.setPrice(price);
        part.setInv(inv);
        part.setMin(10);
        part.setMax(50);

        partService.save(part);
    }

    private void addProduct(String name, double price, int inv) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setInv(inv);

        productService.save(product);
    }
    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int productID, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productID);

        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);

            redirectAttributes.addFlashAttribute(
                    "message",
                    "Purchase successful! 1 unit of \"" + product.getName() + "\" was bought."
            );
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Purchase failed: \"" + product.getName() + "\" is out of stock."
            );
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }

        return "redirect:/mainscreen";
    }
}
