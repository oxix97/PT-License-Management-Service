package com.example.gymservice.repository;

import com.example.gymservice.domain.pass.PassData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassDataRepository extends JpaRepository<PassData, Integer> {

}
