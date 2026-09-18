package br.org.sesisenai.estoqueapi.entity;

import br.org.sesisenai.estoqueapi.enums.RoleUser;
import br.org.sesisenai.estoqueapi.enums.StatusInviteRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name="user_invite")
public class UserInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User invitedByUserId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private RoleUser role;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private StatusInviteRequest status;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;


    public UserInvite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public StatusInviteRequest getStatus() {
        return status;
    }

    public void setStatus(StatusInviteRequest status) {
        this.status = status;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getInvitedByUserId() {
        return invitedByUserId;
    }

    public void setInvitedByUserId(User invitedByUserId) {
        this.invitedByUserId = invitedByUserId;
    }
}
