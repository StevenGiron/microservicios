package com.giron.microservicios.app.examenes.models.repository;

import com.giron.microservicios.app.examenes.models.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamenRepository extends CrudRepository<Examen, Long> {
    @Query("select e from Examen e where e.nombre like %?1%")
    public List<Examen> findByNombre(String termino);


}
