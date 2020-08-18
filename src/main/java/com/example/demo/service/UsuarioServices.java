package com.example.demo.service;

import com.example.demo.data.RoleRepository;
import com.example.demo.data.UsuarioRepository;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import sun.plugin.liveconnect.SecurityContextHelper;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServices implements SecurityService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository rolRepository;

    @Transactional
    public Usuario crearUsuario(Usuario usuario) {

        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return usuario;

    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Transactional
    public Usuario buscarPorNombre(String username) {

        return usuarioRepository.findByUsername(username);

    }

    @Override
    public String findLoggedInUsername() {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {

            return ((UserDetails)userDetails).getUsername();

        }
        return null;

    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("Login funciona para: " + username);
        }
    }

    public long getIDCount() {

        return usuarioRepository.count() + 1;

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Usuario user = usuarioRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Rol role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
