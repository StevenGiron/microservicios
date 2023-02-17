package com.giron.microservicios.app.usuarios.controllers;

import com.giron.microservicios.app.usuarios.services.AlumnoServiceImpl;
import com.giron.microservicios.commons.alumnos.models.entity.Alumno;
import com.giron.microservicios.commons.controllers.CommonController;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoServiceImpl> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, @PathVariable Long id, BindingResult result) {
        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Alumno> alumnoOptional = service.findById(id);
        if (alumnoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = alumnoOptional.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

    @GetMapping("/filtrar/{termino}")
    public ResponseEntity<?> filtrar(@PathVariable String termino){
        return ResponseEntity.ok(service.findByNombreOrApellido(termino));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result,  //RequestBody no va porque no es un Json
                                          @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()){
            alumno.setFoto(archivo.getBytes());
        }
        return super.crear(alumno, result);
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id, //RequestBody no va porque no es un Json
                                           @RequestParam MultipartFile archivo) throws IOException {
        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Alumno> alumnoOptional = service.findById(id);
        if (alumnoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = alumnoOptional.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());
        if(!archivo.isEmpty()){
            alumnoDb.setFoto(archivo.getBytes());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        Optional<Alumno> alumnoOptional = service.findById(id);
        if(alumnoOptional.isEmpty() || alumnoOptional.get().getFoto() == null){
            return ResponseEntity.notFound().build();
        }
        Resource imagen = new ByteArrayResource(alumnoOptional.get().getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
}
