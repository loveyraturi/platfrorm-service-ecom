package com.datafroth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datafroth.modal.Taxes;

@Repository
public interface TaxesRepository  extends JpaRepository<Taxes, Integer> {
}
