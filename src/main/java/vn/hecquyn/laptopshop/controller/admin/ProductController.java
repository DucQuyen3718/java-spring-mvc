package vn.hecquyn.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hecquyn.laptopshop.domain.Product;
import vn.hecquyn.laptopshop.service.ProductService;
import vn.hecquyn.laptopshop.service.UploadService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> prs = this.productService.fetchProducts();
        model.addAttribute("products", prs);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String handleCreateProduct(Model modeld, @Valid @ModelAttribute("newProduct") Product pr, BindingResult newProductBindingResult, @RequestParam("hoidanitFile") MultipartFile file) {
        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }

        String image = this.uploadService.handleSaveUploadFile(file, "product");
        pr.setImage(image);

        this.productService.createProduct(pr);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(@PathVariable("id") long id, Model model) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "/admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(@PathVariable("id") long id, Model model) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("newProduct", pr);
        model.addAttribute("id", id);
        return "/admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String handleUpdateProduct(Model model, @ModelAttribute("newProduct") @Valid Product pr, BindingResult newProductBindingResult, @RequestParam("hoidanitFile") MultipartFile file) {

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/update";
        }

        Product currentProduct = this.productService.fetchProductById(pr.getId()).get();
        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }

            currentProduct.setName(pr.getName());
            currentProduct.setPrice(pr.getPrice());
            currentProduct.setQuantity(pr.getQuantity());
            currentProduct.setDetailDesc(pr.getDetailDesc());
            currentProduct.setShortDesc(pr.getShortDesc());
            currentProduct.setFactory(pr.getFactory());
            currentProduct.setTarget(pr.getTarget());

            this.productService.createProduct(currentProduct);
        }

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getProductDetailPage(Model model, @PathVariable("id") long id) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("id", id);
        model.addAttribute("newProduct", pr);
        return "/admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(@ModelAttribute("newProduct") Product pr) {
        this.productService.deleteProduct(pr.getId());
        return "redirect:/admin/product";
    }

}
