package org.gestioncontratos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
    @Bean
    public UserDetailsManager customUsers(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select login, clave, status from usuarios where login = ?");
        users.setAuthoritiesByUsernameQuery("select u.login, r.nombre from usuario_rol ur " +
                "inner join usuarios u on u.id = ur.usuario_id " +
                "inner join roles r on r.id = ur.rol_id " +
                "where u.login = ?");

        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
                // aperturar el acceso a los recursos estáticos
                .requestMatchers("/assets/**", "/css/**", "/js/**").permitAll()

                // las vistas públicas no requieren autenticación
                .requestMatchers("/", "/privacy", "/terms").permitAll()

                // Asignar permisos a URLs por ROLES
                .requestMatchers("/clientes/**").hasAnyAuthority("admin")
                .requestMatchers("/servicios/**").hasAnyAuthority("admin")
                .requestMatchers("/contratos/**").hasAnyAuthority("admin", "empleado")

                // todas las demás vistas requieren autenticación
                .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
