package ra.example.demo.service;

import org.springframework.web.multipart.MultipartFile;
import ra.example.demo.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
    boolean save(Product product, MultipartFile file);
    boolean delete(Long id);
}
