package br.org.sesisenai.estoqueapi.service;

import br.org.sesisenai.estoqueapi.entity.User;
import br.org.sesisenai.estoqueapi.entity.UserInvite;
import br.org.sesisenai.estoqueapi.enums.StatusInviteRequest;
import br.org.sesisenai.estoqueapi.repository.UserInviteRepository;
import br.org.sesisenai.estoqueapi.repository.UserRepository;
import br.org.sesisenai.estoqueapi.config.JwtService; // Importe o seu JwtService
import org.springframework.security.crypto.password.PasswordEncoder; // Import correto do Spring Security
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserInviteRepository userInviteRepository;
    private final PasswordEncoder passwordEncoder; // Corrigido o nome
    private final JwtService jwtService; // Adicionada a dependência do JWT

    // Construtor atualizado recebendo todas as dependências
    public AuthService(UserRepository userRepository,
                       UserInviteRepository userInviteRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.userInviteRepository = userInviteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public void registerViaInvite(String token, String name, String rawPassword) {
        // Validar se convite existe
        UserInvite invite = userInviteRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Convite inválido ou inexistente."));

        //Validar se esta pendente ou expirou
        if(invite.getStatus() != StatusInviteRequest.PENDING){
            throw new IllegalArgumentException("Este convite já foi utilizado ou cancelado.");
        }
        if(invite.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Este convite expirou.");
        }

        User newUser = new User();
        newUser.setEmail(invite.getEmail());
        newUser.setName(name);
        newUser.setRole(invite.getRole());
        newUser.setPasswordHash(passwordEncoder.encode(rawPassword)); // Usando a variável corrigida

        userRepository.save(newUser);

        invite.setStatus(StatusInviteRequest.APPROVED);
        userInviteRepository.save(invite);
    }

    @Transactional
    public String login(String email, String rawPassword){ // Corrigido rawPasswort para rawPassword
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Credenciais inválidas."));

        if(!passwordEncoder.matches(rawPassword, user.getPasswordHash())) { // Usando a variável corrigida
            throw new IllegalArgumentException("Credenciais inválidas.");
        }

        return jwtService.generateToken(user); // Agora o jwtService existe na classe
    }
}