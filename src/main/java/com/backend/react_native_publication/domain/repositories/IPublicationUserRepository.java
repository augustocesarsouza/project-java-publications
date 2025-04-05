package com.backend.react_native_publication.domain.repositories;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.domain.entities.PublicationUser;

import java.util.List;
import java.util.UUID;

public interface IPublicationUserRepository {
    List<PublicationUserDTO> GetAllPublicationUser();
    PublicationUserDTO GetPublicationByUserId(UUID userId);
    PublicationUser create(PublicationUser user);
    PublicationUser update(PublicationUser user);
    PublicationUser delete(UUID publicationId);
}
