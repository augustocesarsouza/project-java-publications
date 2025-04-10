package com.backend.react_native_publication.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateValidatorDTO {
    @JsonProperty("id")
    private UUID Id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, message = "password should have at last 3 characters")
    @JsonProperty("name")
    private String Name;
    @NotEmpty(message = "email should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "email must be a valid Gmail address")
    @JsonProperty("email")
    private String Email;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, message = "password should have at last 8 characters")
    @JsonProperty("password")
    private String Password;

    @JsonProperty("base64ImageUser")
    private String Base64ImageUser;

    public UserCreateValidatorDTO() {
    }

    public UserCreateValidatorDTO(UUID id, String name, String email, String password, String base64ImageUser) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
        Base64ImageUser = base64ImageUser;
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

    public String getPassword() {
        return Password;
    }

    public String getBase64ImageUser() {
        return Base64ImageUser;
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

    public void setPassword(String password) {
        Password = password;
    }

    public void setBase64ImageUser(String base64ImageUser) {
        Base64ImageUser = base64ImageUser;
    }
}
