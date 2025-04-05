package com.backend.react_native_publication.application.services.interfaces;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.application.dto.validations.publicationUserDTOs.PublicationUserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IPublicationUserService {
    ResultService<List<PublicationUserDTO>> GetAllPublicationUser();
    ResultService<PublicationUserDTO> GetPublicationByUserId(UUID userId);
    ResultService<PublicationUserDTO> Create(PublicationUserCreateValidatorDTO publicationUserCreateValidatorDTO, BindingResult result);
}
