package com.backend.react_native_publication.application.dto.validations.publicationUserDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationUserCreateValidatorDTO {
    @NotEmpty(message = "text should not be empty")
    @JsonProperty("text")
    private String Text;

    @JsonProperty("publicationImg")
    private String PublicationImg;

    @NotEmpty(message = "userId should not be empty")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
            message = "userId must be a valid UUID")
    @JsonProperty("userId")
    private UUID UserId;

    public PublicationUserCreateValidatorDTO(String text, String publicationImg, UUID userId) {
        Text = text;
        PublicationImg = publicationImg;
        UserId = userId;
    }

    public PublicationUserCreateValidatorDTO() {
    }

    public String getText() {
        return Text;
    }

    public String getPublicationImg() {
        return PublicationImg;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setText(String text) {
        Text = text;
    }

    public void setPublicationImg(String publicationImg) {
        PublicationImg = publicationImg;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }
}
