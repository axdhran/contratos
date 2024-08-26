package org.gestioncontratos.repositorios;

import org.gestioncontratos.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}
