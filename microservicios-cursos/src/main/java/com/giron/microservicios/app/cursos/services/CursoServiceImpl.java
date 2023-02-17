package com.giron.microservicios.app.cursos.services;

import com.giron.microservicios.app.cursos.models.entity.Curso;
import com.giron.microservicios.app.cursos.models.repository.CursoRepository;
import com.giron.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements ICursoService {
    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
