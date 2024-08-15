package org.gestioncontratos.repositorios;

import org.gestioncontratos.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository  extends JpaRepository<Cliente, Integer> {
}
