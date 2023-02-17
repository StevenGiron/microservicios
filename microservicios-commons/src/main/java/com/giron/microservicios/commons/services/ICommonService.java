package com.giron.microservicios.commons.services;

import java.util.Optional;

public interface ICommonService<E> {// E: hace referencia a la clase entity
    public Iterable<E> findAll();

    public Optional<E> findById(Long id);

    public E save(E entity);

    public void deleteById(Long id);
}
