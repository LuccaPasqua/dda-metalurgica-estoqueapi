package br.org.sesisenai.estoqueapi.dto;

import br.org.sesisenai.estoqueapi.enums.RoleUser;
import br.org.sesisenai.estoqueapi.enums.StatusInviteRequest;

import java.time.LocalDateTime;

public record UserInviteResponseDTO(
        Long id,
        Long invitedByUserId,
        String email,
        RoleUser role,
        StatusInviteRequest status,
        LocalDateTime expiresAt,
        LocalDateTime createdAt
) {
}
