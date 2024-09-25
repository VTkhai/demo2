package com.example.demo2.repository;

import com.example.demo2.entity.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
//    @Query("SELECT * FROM supplier_entity WHERE ..")
//    @Query("SELECT o FROM Supplier WHERE o.name = :name")
    List<Supplier> findByNameContainingIgnoreCase(String name);
    List<Supplier> findByAddressContainingIgnoreCase(String address);
}
