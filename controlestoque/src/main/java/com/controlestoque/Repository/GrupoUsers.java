package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.model.GrupoUser;

public interface GrupoUsers extends JpaRepository<GrupoUser, Long> {

}
