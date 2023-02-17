package com.giron.microservicios.app.cursos.models.repository;

import com.giron.microservicios.app.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Query("select c from Curso c join fetch c.alumnos a where a.id =?1") //inner join para traer el curso segun el id del alumno que pertenece a ese curso
    public Curso findCursoByAlumnoId(Long id);
}
