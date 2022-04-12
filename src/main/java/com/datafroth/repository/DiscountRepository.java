package com.datafroth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datafroth.modal.Discounts;

@Repository
public interface DiscountRepository  extends JpaRepository<Discounts,Integer> {

}
