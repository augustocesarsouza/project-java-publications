package com.backend.react_native_publication.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_publication_user", schema = "public")
public class PublicationUser {
    @jakarta.persistence.Id
    @Column(name = "publication_user_id")
    @JsonProperty("id")
    private UUID Id;

    @Column(name = "text")
    @JsonProperty("text")
    private String Text;

    @Column(name = "publication_date")
    @JsonProperty("publicationDate")
    private LocalDateTime PublicationDate;

    @Column(name = "publication_img")
    @JsonProperty("publicationImg")
    private String PublicationImg;

    @Column(name = "comments_count")
    @JsonProperty("commentsCount")
    private Integer CommentsCount;

    @Column(name = "user_id")
    @JsonProperty("userId")
    private UUID UserId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User User;

    public PublicationUser(UUID id, String text, LocalDateTime publicationDate, String publicationImg, Integer commentsCount, UUID userId, User user) {
        Id = id;
        Text = text;
        PublicationDate = publicationDate;
        PublicationImg = publicationImg;
        CommentsCount = commentsCount;
        UserId = userId;
        User = user;
    }

    public PublicationUser() {
    }

    public UUID getId() {
        return Id;
    }

    public String getText() {
        return Text;
    }

    public LocalDateTime getPublicationDate() {
        return PublicationDate;
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

    public User getUser() {
        return User;
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

    public void setUser(User user) {
        User = user;
    }
}
