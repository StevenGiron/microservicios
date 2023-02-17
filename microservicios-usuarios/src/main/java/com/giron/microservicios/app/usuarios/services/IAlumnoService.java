package com.giron.microservicios.app.usuarios.services;

import com.giron.microservicios.commons.alumnos.models.entity.Alumno;
import com.giron.microservicios.commons.services.ICommonService;

import java.util.List;


public interface IAlumnoService extends ICommonService<Alumno> {
    public List<Alumno> findByNombreOrApellido (String termino);
}
