package com.giron.microservicios.app.examenes.controllers;

import com.giron.microservicios.app.examenes.models.entity.Examen;
import com.giron.microservicios.app.examenes.services.ExamenService;
import com.giron.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id) {
        Optional<Examen> optionalExamen = service.findById(id);
        if (optionalExamen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = optionalExamen.get();
        examenDb.setNombre(examen.getNombre());

//        Vemos si el examenDb contiene preguntas que ya no existen en el examen del request para eliminarla

//        List<Pregunta> preguntasEliminadas = new ArrayList<>();
//        examenDb.getPreguntas().forEach(preguntadB -> {
//            if(!examen.getPreguntas().contains(preguntadB)){
//                preguntasEliminadas.add(preguntadB);
//            }
//        });
//
//        preguntasEliminadas.forEach(examenDb::removePregunta);

        examenDb.getPreguntas().stream()
                .filter(preguntadB -> !examen.getPreguntas().contains(preguntadB))
                .forEach(examenDb::removePregunta);
        ;

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/filtrar/{termino}")
    public ResponseEntity<?> filtrar (@PathVariable String termino){
        return ResponseEntity.ok(service.findByNombre(termino));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok(service.findAllAsignaturas());
    }
}
