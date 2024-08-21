package org.gestioncontratos.repositorios;

import org.gestioncontratos.modelos.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioRepository extends JpaRepository<Servicio, Integer> {
}
