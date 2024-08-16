package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entities.Executive;

public interface ExecutiveDAO extends JpaRepository<Executive, String> {
    Executive findByEname(String ename);

    Executive findByEid(String eid); // Method to find by ID
}
