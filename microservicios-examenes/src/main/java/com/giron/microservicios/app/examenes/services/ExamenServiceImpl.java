package com.giron.microservicios.app.examenes.services;

import com.giron.microservicios.app.examenes.models.entity.Asignatura;
import com.giron.microservicios.app.examenes.models.entity.Examen;
import com.giron.microservicios.app.examenes.models.repository.AsignaturaRepository;
import com.giron.microservicios.app.examenes.models.repository.ExamenRepository;
import com.giron.microservicios.commons.services.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String termino) {
        return repository.findByNombre(termino);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllAsignaturas() {
        return (List<Asignatura>) asignaturaRepository.findAll();
    }
}
