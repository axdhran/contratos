package org.gestioncontratos.servicios.implementaciones;

import org.gestioncontratos.modelos.Rol;
import org.gestioncontratos.repositorios.IRolRepository;
import org.gestioncontratos.servicios.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }
}
