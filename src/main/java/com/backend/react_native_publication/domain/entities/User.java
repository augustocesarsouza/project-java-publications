package com.backend.react_native_publication.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users", schema = "public")
public class User implements UserDetails {
    @jakarta.persistence.Id
    @Column(name = "user_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "name")
    @JsonProperty("name")
    private String Name;
    @Column(name = "email")
    @JsonProperty("email")
    private String Email;
    @Column(name = "password_hash")
    @JsonProperty("passwordHash")
    private String PasswordHash;
    @Column(name = "confirm_email")
    @JsonProperty("confirmEmail")
    private Short ConfirmEmail;
    @Column(name = "user_image")
    @JsonProperty("userImage")
    private String UserImage;

    public User() {
    }

    public User(UUID id, String name, String email, String passwordHash, Short confirmEmail, String userImage) {
        Id = id;
        Name = name;
        Email = email;
        PasswordHash = passwordHash;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
    }

    public void setValuesUser(String name, String email, String passwordHash, Short confirmEmail, String userImage) {
        Name = name;
        Email = email;
        PasswordHash = passwordHash;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public Short getConfirmEmail() {
        return ConfirmEmail;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public void setConfirmEmail(Short confirmEmail) {
        ConfirmEmail = confirmEmail;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return PasswordHash;
    }

    @Override
    public String getUsername() {
        return Email;
    }

}
