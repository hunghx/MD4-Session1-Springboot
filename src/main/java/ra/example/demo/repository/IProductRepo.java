package ra.example.demo.repository;

import ra.example.demo.model.entity.Product;

import java.util.List;

public interface IProductRepo {
    List<Product> findAll();
    Product findById(Long id);
    boolean save(Product product);
    boolean delete(Long id);
}
