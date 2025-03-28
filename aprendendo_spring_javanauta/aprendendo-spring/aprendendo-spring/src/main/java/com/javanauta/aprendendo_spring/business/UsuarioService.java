package com.javanauta.aprendendo_spring.business;

import com.javanauta.aprendendo_spring.infraestructure.entity.Usuario;
import com.javanauta.aprendendo_spring.infraestructure.exceptions.ConflictException;
import com.javanauta.aprendendo_spring.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.aprendendo_spring.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // faz injeção de dependecias
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExista(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        } catch (ConflictException e) {
            throw new ConflictException("Email ja cadastrado", e.getCause());
        }
    }

    public void emailExista(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email ja cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email ja cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Email não encontrado! " + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deletePorEmail(email);
    }


}
