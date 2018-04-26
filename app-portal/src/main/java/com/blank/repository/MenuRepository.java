package com.blank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blank.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
