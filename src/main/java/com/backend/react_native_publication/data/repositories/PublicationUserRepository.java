package com.backend.react_native_publication.data.repositories;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.data.context.PublicationUserJPA;
import com.backend.react_native_publication.domain.entities.PublicationUser;
import com.backend.react_native_publication.domain.repositories.IPublicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PublicationUserRepository implements IPublicationUserRepository {
    private final PublicationUserJPA publicationUserJPA;

    @Autowired
    public PublicationUserRepository(PublicationUserJPA publicationUserJPA) {
        this.publicationUserJPA = publicationUserJPA;
    }

    @Override
    public List<PublicationUserDTO> GetAllPublicationUser() {
        return publicationUserJPA.GetAllPublicationUser();
    }

    @Override
    public PublicationUserDTO GetPublicationByUserId(UUID userId) {
        return publicationUserJPA.GetPublicationByUserId(userId);
    }

    @Override
    public PublicationUser create(PublicationUser publicationUser) {
        if(publicationUser == null)
            return null;

        return publicationUserJPA.save(publicationUser);
    }

    @Override
    public PublicationUser update(PublicationUser user) {
        return null;
    }

    @Override
    public PublicationUser delete(UUID publicationId) {
        if(publicationId == null)
            return null;

        PublicationUser publicationUser = publicationUserJPA.findById(publicationId).orElse(null);

        if(publicationUser == null)
            return null;

        publicationUserJPA.delete(publicationUser);

        return publicationUser;
    }
}
