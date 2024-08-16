package org.gestioncontratos.servicios.interfaces;

import org.gestioncontratos.modelos.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Page<Cliente> buscarTodosPaginados(Pageable pageable);

    List<Cliente> obtenerTodos();

    Optional<Cliente> buscarPorId(Integer id);

    Cliente createOrEdit(Cliente cliente);

    void eliminarPorId(Integer id);
}
