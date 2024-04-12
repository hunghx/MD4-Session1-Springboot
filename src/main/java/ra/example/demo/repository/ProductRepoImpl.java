package ra.example.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ra.example.demo.model.entity.Product;

import java.util.List;

@Repository
@Transactional
public class ProductRepoImpl implements IProductRepo{
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    public boolean save(Product product) {
        if (product.getId()==null){
            // them moi
            entityManager.persist(product);
        }else {
            // cap nhat
            entityManager.merge(product);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        entityManager.remove(findById(id));
        return true;
    }
}
