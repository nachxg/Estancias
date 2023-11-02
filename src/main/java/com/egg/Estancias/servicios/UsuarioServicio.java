package com.egg.Estancias.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.egg.Estancias.entidades.Usuario;
import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.repositorios.UsuarioRepositorio;
import com.egg.Estancias.rol.Rol;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String username, String email, String clave, String clave2) throws MiExcepcion {

        validar(username, email, clave, clave2);

        Usuario usuario = new Usuario();

        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setClave(new BCryptPasswordEncoder().encode(clave));
        usuario.setFechaAlta(new Date());
        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);

    }

    public List<Usuario> consultarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioRepositorio.findAll();

        return usuarios;
    }

    @Transactional
    public void eliminarUsuario(Integer idUsuario) {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            usuarioRepositorio.deleteById(idUsuario);
        }

    }

    private void validar(String username, String email, String clave, String clave2) throws MiExcepcion {

        if (username.isEmpty() || username == null) {
            throw new MiExcepcion("El nombre de usuario no puede ser nulo o estar vacío.");
        }

        if (email.isEmpty() || email == null) {
            throw new MiExcepcion("El email no puede ser nulo o estar vacío.");
        }

        if (clave.isEmpty() || clave == null || clave.length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacía, y debe tener más de 5 dígitos.");
        }

        if (!clave.equals(clave2)) {
            throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales.");
        }

        if(usuarioRepositorio.buscarPorEmail(email) != null){
            throw new MiExcepcion("El correo electronico " + email + " ya se encuentra registrado.");
        }

        if(usuarioRepositorio.buscarPorUsuario(username) != null){
            throw new MiExcepcion("El nombre de usuario " + username + " ya se encuentra registrado.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(username);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getClave(), permisos);
        } else {
            return null;
        }
    }
}
