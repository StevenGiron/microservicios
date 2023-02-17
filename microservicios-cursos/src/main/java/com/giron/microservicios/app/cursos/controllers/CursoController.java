package com.giron.microservicios.app.cursos.controllers;

//import com.giron.microservicios.commons.alumnos.models.entity.Alumno;
import com.giron.microservicios.app.cursos.models.entity.Alumno;
import com.giron.microservicios.app.cursos.models.entity.Curso;
import com.giron.microservicios.app.cursos.models.entity.Examen;
import com.giron.microservicios.app.cursos.services.CursoServiceImpl;
import com.giron.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoServiceImpl> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> optionalCurso = this.service.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = optionalCurso.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/asignar-alumnnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
        Optional<Curso> optionalCurso = this.service.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = optionalCurso.get();
        alumnos.forEach(a -> {
            dbCurso.addAlumno(a);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-alumnno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
        Optional<Curso> optionalCurso = this.service.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = optionalCurso.get();

        dbCurso.removeAlumno(alumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findCursoByAlumnoId(id));
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
        Optional<Curso> optionalCurso = this.service.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = optionalCurso.get();
        examenes.forEach(e -> {
            dbCurso.addExamen(e);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
        Optional<Curso> optionalCurso = this.service.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = optionalCurso.get();

        dbCurso.removeExamen(examen);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
}
