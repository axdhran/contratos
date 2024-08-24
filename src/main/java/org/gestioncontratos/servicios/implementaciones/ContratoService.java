package org.gestioncontratos.servicios.implementaciones;

import org.gestioncontratos.modelos.Contrato;
import org.gestioncontratos.repositorios.IContratoRepository;
import org.gestioncontratos.servicios.interfaces.IContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService implements IContratoService {

    @Autowired
    private IContratoRepository contratoRepository;

    @Override
    public List<Contrato> obtenerTodos(){return contratoRepository.findAll();}

    @Override
    public Page<Contrato> buscarTodosPaginados(Pageable pageable) {
        return contratoRepository.findByOrderByClienteDesc(pageable);
    }

    @Override
    public Contrato buscarPorId(Integer id) {return contratoRepository.findById(id).get(); }


    @Override
    public Contrato crearOEditar(Contrato contrato) {return contratoRepository.save(contrato);}

    @Override
    public void eliminarPorId(Integer id) {contratoRepository.deleteById(id);}

}
