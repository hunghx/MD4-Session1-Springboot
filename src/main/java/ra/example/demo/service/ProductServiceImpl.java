package ra.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.example.demo.model.entity.Product;
import ra.example.demo.repository.IProductRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepo productRepo;
    private final UploadService uploadService;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public boolean save(Product product, MultipartFile file) {
        if (product.getId() == null) {
            product.setSku(UUID.randomUUID().toString());
        } else {
            product.setImg(productRepo.findById(product.getId()).getImg());
        }
        if (file.getSize() > 0) {
            product.setImg(uploadService.uploadFileToServer(file));
        }
        return productRepo.save(product);
    }

    @Override
    public boolean delete(Long id) {
        return productRepo.delete(id);
    }
}
