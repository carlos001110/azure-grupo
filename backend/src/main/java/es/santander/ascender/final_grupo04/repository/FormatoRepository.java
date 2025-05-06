package es.santander.ascender.final_grupo04.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.final_grupo04.model.Formato;

public interface FormatoRepository extends JpaRepository<Formato, Long> {

    // List<Formato> findByTipoId(Long tipo_id);
    Optional<Formato> findByNombre(String nombre);
}
