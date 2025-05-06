package es.santander.ascender.final_grupo04.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.santander.ascender.final_grupo04.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByEstadoTrue(); // Lista ítems disponibles

    @Query("SELECT i FROM Item i WHERE "
            + "(:titulo IS NULL OR i.titulo LIKE %:titulo%) AND "
            + "(:tipo IS NULL OR i.tipo.nombre = :tipo) AND "
            + "(:ubicacion IS NULL OR i.ubicacion LIKE %:ubicacion%)")
    List<Item> buscarPorCriterios(@Param("titulo") String titulo,
            @Param("tipo") String tipo,
            @Param("ubicacion") String ubicacion,
            Sort sort);

    Page<Item> findByTituloContainingIgnoreCaseAndTipo_NombreContainingIgnoreCaseAndUbicacionContainingIgnoreCase(
            String titulo, String tipo, String ubicacion, Pageable pageable);

}
