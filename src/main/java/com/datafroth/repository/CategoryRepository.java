package com.datafroth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datafroth.modal.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
