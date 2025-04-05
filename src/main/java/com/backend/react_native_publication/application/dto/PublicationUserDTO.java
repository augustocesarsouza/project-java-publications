package com.backend.react_native_publication.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class PublicationUserDTO {
    @JsonProperty("id")
    private UUID Id;

    @JsonProperty("text")
    private String Text;

    @JsonProperty("publicationDate")
    private LocalDateTime PublicationDate;

    @JsonProperty("publicationImg")
    private String PublicationImg;

    @JsonProperty("commentsCount")
    private Integer CommentsCount;

    @JsonProperty("userId")
    private UUID UserId;

    @JsonProperty("userDTO")
    private UserDTO UserDTO;

    public PublicationUserDTO(UUID id, String text, LocalDateTime publicationDate, String publicationImg, Integer commentsCount, UUID userId, UserDTO userDTO) {
        Id = id;
        Text = text;
        PublicationDate = publicationDate;
        PublicationImg = publicationImg;
        CommentsCount = commentsCount;
        UserId = userId;
        UserDTO = userDTO;
    }

    public PublicationUserDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public LocalDateTime getPublicationDate() {
        return PublicationDate;
    }

    public String getText() {
        return Text;
    }

    public String getPublicationImg() {
        return PublicationImg;
    }

    public Integer getCommentsCount() {
        return CommentsCount;
    }

    public UUID getUserId() {
        return UserId;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setText(String text) {
        Text = text;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        PublicationDate = publicationDate;
    }

    public void setPublicationImg(String publicationImg) {
        PublicationImg = publicationImg;
    }

    public void setCommentsCount(Integer commentsCount) {
        CommentsCount = commentsCount;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }
}
