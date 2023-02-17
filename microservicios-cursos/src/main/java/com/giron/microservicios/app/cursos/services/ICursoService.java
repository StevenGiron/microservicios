package com.giron.microservicios.app.cursos.services;

import com.giron.microservicios.app.cursos.models.entity.Curso;
import com.giron.microservicios.commons.services.ICommonService;

public interface ICursoService extends ICommonService<Curso> {
    public Curso findCursoByAlumnoId(Long id);

}
