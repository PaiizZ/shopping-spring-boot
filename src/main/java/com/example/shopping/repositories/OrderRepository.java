package com.example.shopping.repositories;

import com.example.shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT '*' From orders left join order_product op on op.order_id = ?", nativeQuery = true)
    List<Object> findByOrderId(Long id);
}
