package org.gestioncontratos.servicios.interfaces;

import org.gestioncontratos.modelos.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IServicioService {
    Page<Servicio> buscarTodosPaginados(Pageable pageable);

    List<Servicio> obtenerTodos();

    Optional<Servicio> buscarPorId(Integer id);

    Servicio createOrEdit(Servicio cliente);

    void eliminarPorId(Integer id);
}
