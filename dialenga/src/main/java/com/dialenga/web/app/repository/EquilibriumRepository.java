package com.dialenga.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dialenga.web.app.models.EquilibriumBean;

@Repository
public interface EquilibriumRepository extends JpaRepository<EquilibriumBean, Long>{

}
