package com.giron.microservicios.app.examenes.services;

import com.giron.microservicios.app.examenes.models.entity.Asignatura;
import com.giron.microservicios.app.examenes.models.entity.Examen;
import com.giron.microservicios.commons.services.ICommonService;

import java.util.List;

public interface ExamenService extends ICommonService<Examen> {
    public List<Examen> findByNombre(String termino);

    public List<Asignatura> findAllAsignaturas();
}
