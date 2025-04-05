package com.backend.react_native_publication;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.application.dto.UserDTO;
import com.backend.react_native_publication.domain.entities.PublicationUser;
import com.backend.react_native_publication.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ModelConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
            .setAmbiguityIgnored(true)
            .setPropertyCondition(context -> context.getSource() != null);

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setPasswordHash(source.getPasswordHash());
                map().setConfirmEmail(source.getConfirmEmail());
                map().setUserImage(source.getUserImage());
            }
        });

        modelMapper.addMappings(new PropertyMap<PublicationUser, PublicationUserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setText(source.getText());
                map().setPublicationDate(source.getPublicationDate());
                map().setPublicationImg(source.getPublicationImg());
                map().setCommentsCount(source.getCommentsCount());
                map().setUserId(source.getUserId());

                when(Objects::nonNull)
                        .map(source.getUser(), destination.getUserDTO());
            }
        });

        return modelMapper;
    }
}
//PublicationUser(UUID id, String text, LocalDateTime publicationDate, String publicationImg, Integer commentsCount, UUID userId, User user)