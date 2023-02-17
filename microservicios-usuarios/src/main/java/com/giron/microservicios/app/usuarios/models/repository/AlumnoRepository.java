package com.giron.microservicios.app.usuarios.models.repository;

import com.giron.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

    @Query("select a from Alumno a where upper(a.nombre) like upper(concat('%', ?1, '%')) or upper(a.apellido) like upper(concat('%', ?1, '%'))")
    public List<Alumno> findByNombreOrApellido (String termino);
}
