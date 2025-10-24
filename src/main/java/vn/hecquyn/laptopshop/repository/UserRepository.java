package vn.hecquyn.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hecquyn.laptopshop.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User save(User hoidanit);
}
