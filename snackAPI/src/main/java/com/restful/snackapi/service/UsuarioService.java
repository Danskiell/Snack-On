package com.restful.snackapi.service;
import com.restful.snackapi.model.Usuario;
import com.restful.snackapi.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String SECRET_KEY = "snackonmelhorappdecantinas";


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail_Usuario(email);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void registrarOuAtualizar(String email, String nome) {
        Usuario usuario = usuarioRepository.findByEmail_Usuario(email)
                .orElse(new Usuario());
        usuario.setEmail_Usuario(email);
        usuario.setNome_Usuario(nome);
        usuarioRepository.save(usuario);
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        // Verificando se ja tem esse email no banco
        Optional<Usuario> existente = usuarioRepository.
                findByEmail_Usuario(usuario.getEmail_Usuario());
            if (existente.isPresent()) {
                throw new RuntimeException("Email já cadastrado!");
            }
            // Definindo por padrão que o cargo será cliente
            usuario.setCargo("cliente");

            // Criptografando a senha
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

            // Salvando o usuário com suceso ao banco
            return usuarioRepository.save(usuario);
    }

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail_Usuario())
                .claim("nome", usuario.getNome_Usuario())
                .claim("cargo", usuario.getCargo())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }










}