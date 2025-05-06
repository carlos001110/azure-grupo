package es.santander.ascender.final_grupo04.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.santander.ascender.final_grupo04.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    // Lista de préstamos activos (suponiendo que activo=true o fechaDevolucion es null)
    List<Prestamo> findByActivoTrue();

    // Lista de préstamos activos por persona
    List<Prestamo> findByPersonaAndActivoTrue(String persona);

    // Lista de préstamos activos por fecha exacta de préstamo
    List<Prestamo> findByFechaPrestamoAndActivoTrue(LocalDate fechaPrestamo);

    // Filtrar préstamos activos por persona y rango de fechas
    List<Prestamo> findByPersonaAndFechaPrestamoBetweenAndActivoTrue(String persona, LocalDate desde, LocalDate hasta);

    // Filtrar préstamos activos por rango de fechas (sin persona)
    List<Prestamo> findByFechaPrestamoBetweenAndActivoTrue(LocalDate desde, LocalDate hasta);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaDevolucion IS NULL "
            + "AND (:persona IS NULL OR LOWER(p.persona) LIKE LOWER(CONCAT('%', :persona, '%'))) "
            + "AND (:fechaDesde IS NULL OR p.fechaPrestamo >= :fechaDesde) "
            + "AND (:fechaHasta IS NULL OR p.fechaPrestamo <= :fechaHasta)")
    List<Prestamo> findPrestamosActivosFiltrados(
            @Param("persona") String persona,
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta);

}
