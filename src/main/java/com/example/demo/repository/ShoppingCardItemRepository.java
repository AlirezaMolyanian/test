package com.example.demo.repository;

import com.example.demo.dao.ShoppingCardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCardItemRepository extends JpaRepository<ShoppingCardItem, Long> {
}
