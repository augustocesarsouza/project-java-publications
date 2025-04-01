package com.backend.react_native_publication.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @Getter
    @JsonProperty("id")
    private UUID Id;
    @Getter
    @JsonProperty("name")
    private String Name;
    @Getter
    @JsonProperty("email")
    private String Email;
    @Getter
    @JsonProperty("passwordHash")
    private String PasswordHash;
    @Getter
    @JsonProperty("confirmEmail")
    private Short ConfirmEmail; // se 0 false, 1 true
    @JsonProperty("userImage")
    private String UserImage;
    @Getter
    @JsonProperty("base64ImageUser")
    private String Base64ImageUser;
    @Getter
    @JsonProperty("token")
    private String Token;

    public UserDTO() {
    }

    public UserDTO(UUID id, String name, String email, String passwordHash, Short confirmEmail, String userImage, String base64ImageUser, String token) {
        Id = id;
        Name = name;
        Email = email;
        PasswordHash = passwordHash;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
        Base64ImageUser = base64ImageUser;
        Token = token;
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

    public void setBase64ImageUser(String base64ImageUser) {
        Base64ImageUser = base64ImageUser;
    }

    public void setToken(String token) {
        Token = token;
    }
}
