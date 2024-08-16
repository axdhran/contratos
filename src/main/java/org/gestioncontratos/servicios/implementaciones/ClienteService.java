package org.gestioncontratos.servicios.implementaciones;

import org.gestioncontratos.modelos.Cliente;
import org.gestioncontratos.repositorios.IClienteRepository;
import org.gestioncontratos.servicios.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public Page<Cliente> buscarTodosPaginados(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente createOrEdit(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepository.deleteById(id);
    }
}
