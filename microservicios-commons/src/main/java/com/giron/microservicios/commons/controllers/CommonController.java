package com.giron.microservicios.commons.controllers;


import com.giron.microservicios.commons.services.ICommonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends ICommonService<E>>{

    @Autowired
    protected S service;

    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> entityOptional = service.findById(id);
        if (entityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(entityOptional.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result) { //BindingResult siempre esta despues del entity a validar
        if(result.hasErrors()){
            return this.validar(result);
        }
        E entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), "El Campo " + err.getField() + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
