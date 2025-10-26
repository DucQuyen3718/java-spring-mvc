package vn.hecquyn.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hecquyn.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
