package com.backend.react_native_publication.data.context;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.domain.entities.PublicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublicationUserJPA extends JpaRepository<PublicationUser, UUID> {
    @Query("SELECT new com.backend.react_native_publication.application.dto." +
            "PublicationUserDTO(x.Id, x.PublicationDate, x.PublicationImg, x.CommentsCount, x.UserId, null) " +
            "FROM PublicationUser AS x")
    List<PublicationUserDTO> GetAllPublicationUser();

    @Query("SELECT new com.backend.react_native_publication.application.dto." +
            "PublicationUserDTO(x.Id, x.PublicationDate, x.PublicationImg, x.CommentsCount, x.UserId, null) " +
            "FROM PublicationUser AS x " +
            "WHERE x.UserId = :userId")
    PublicationUserDTO GetPublicationByUserId(UUID userId);
}
//PublicationUser(UUID id, LocalDateTime publicationDate, String publicationImg, Integer commentsCount, UUID userId, User user)