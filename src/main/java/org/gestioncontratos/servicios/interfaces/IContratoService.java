package org.gestioncontratos.servicios.interfaces;

import org.gestioncontratos.modelos.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContratoService {
    List<Contrato> obtenerTodos();

    Page<Contrato> buscarTodosPaginados(Pageable pageable);

    Contrato buscarPorId(Integer id);

    Contrato crearOEditar(Contrato contrato);

    void eliminarPorId(Integer id);
}
