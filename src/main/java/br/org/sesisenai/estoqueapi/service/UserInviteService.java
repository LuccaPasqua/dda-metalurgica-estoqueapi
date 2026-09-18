package br.org.sesisenai.estoqueapi.service;

import br.org.sesisenai.estoqueapi.dto.UserInviteRequestDTO;
import br.org.sesisenai.estoqueapi.entity.User;
import br.org.sesisenai.estoqueapi.entity.UserInvite;
import br.org.sesisenai.estoqueapi.enums.StatusInviteRequest;
import br.org.sesisenai.estoqueapi.repository.UserInviteRepository;
import br.org.sesisenai.estoqueapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInviteService {

    private UserInviteRepository userInviteRepository;
    private UserRepository userRepository;

    public UserInviteService(
         UserInviteRepository userInviteRepository,
         UserRepository userRepository
    ) {
        this.userInviteRepository = userInviteRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserInvite> findAll(){
       List<UserInvite> userInviteList = userInviteRepository.findAll();

       return userInviteList;
    }

    @Transactional
    public void revoke(Long id){
        UserInvite userInvite = userInviteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não existe nenhum convite com ID: "+ id));

        if(userInvite.getStatus().equals(StatusInviteRequest.PENDING)){
            userInvite.setStatus(StatusInviteRequest.CANCELLED);
            userInviteRepository.save(userInvite);
        }
    }

    @Transactional
    public UserInvite create(UserInviteRequestDTO dto){
        Optional<User> user = userRepository.findUserByEmail(dto.email());

        if(user.isPresent()){
            throw new IllegalArgumentException("Não é possível enviar convite, cliente já existe com email: " + dto.email());
        }

        List<UserInvite> userInvites = userInviteRepository.findByEmail(dto.email());

        userInvites.stream()
                .filter(invite -> invite.getStatus() == StatusInviteRequest.PENDING)
                .forEach(invite -> invite.setStatus(StatusInviteRequest.CANCELLED));

        Optional<User> userHowInvited = userRepository.findById(dto.invitedByUserId());

        // Temos que melhorar isso, pois na verdade isso não deve ser enviado pelo cliente e sim o sistema ver de acordo com o cliente que está logado.

        if(!userHowInvited.isPresent()){
            throw new IllegalArgumentException("Erro ao criar convite!");
        }

        UserInvite userInvite = new UserInvite();
        userInvite.setStatus(StatusInviteRequest.PENDING);
        userInvite.setEmail(dto.email());
        userInvite.setRole(dto.role());
        userInvite.setInvitedByUserId(userHowInvited.get());
        userInvite.setToken(UUID.randomUUID().toString());
        userInvite.setExpiresAt(LocalDateTime.now().plusDays(7));

        UserInvite savedUserInvite = userInviteRepository.save(userInvite);
        return savedUserInvite;
    }
}
