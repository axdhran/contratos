package org.gestioncontratos.repositorios;

import org.gestioncontratos.modelos.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContratoRepository extends JpaRepository<Contrato, Integer> {
    Page<Contrato> findByOrderByClienteDesc (Pageable pageable);
}
