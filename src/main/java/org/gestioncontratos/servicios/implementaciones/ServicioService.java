package org.gestioncontratos.servicios.implementaciones;

import org.gestioncontratos.modelos.Servicio;
import org.gestioncontratos.repositorios.IServicioRepository;
import org.gestioncontratos.servicios.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    @Override
    public Page<Servicio> buscarTodosPaginados(Pageable pageable) {
        return servicioRepository.findAll(pageable);
    }

    @Override
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> buscarPorId(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    public Servicio createOrEdit(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarPorId(Integer id) {
        servicioRepository.deleteById(id);
    }
}
