package es.santander.ascender.final_grupo04.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.final_grupo04.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

    Optional<Tipo> findByNombre(String nombre);
}
