package com.giron.microservicios.app.usuarios.services;

import com.giron.microservicios.commons.alumnos.models.entity.Alumno;
import com.giron.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.giron.microservicios.commons.services.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements IAlumnoService {

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String termino) {
        return repository.findByNombreOrApellido(termino);
    }
}
