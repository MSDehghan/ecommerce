package com.bestretail.ecommerce.user.repository;

import com.bestretail.ecommerce.user.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
