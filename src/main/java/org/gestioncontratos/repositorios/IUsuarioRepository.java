package org.gestioncontratos.repositorios;

import org.gestioncontratos.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
