package br.org.sesisenai.estoqueapi.dto;

import br.org.sesisenai.estoqueapi.enums.RoleUser;
import br.org.sesisenai.estoqueapi.enums.StatusInviteRequest;

public record UserInviteRequestDTO(
        Long invitedByUserId,
        String email,
        RoleUser role,
        StatusInviteRequest status
) {
}
