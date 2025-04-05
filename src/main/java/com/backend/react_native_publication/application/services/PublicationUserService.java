package com.backend.react_native_publication.application.services;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.react_native_publication.application.dto.validations.publicationUserDTOs.PublicationUserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.interfaces.IPublicationUserService;
import com.backend.react_native_publication.domain.entities.PublicationUser;
import com.backend.react_native_publication.domain.repositories.IPublicationUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PublicationUserService implements IPublicationUserService {
    private final IPublicationUserRepository publicationUserRepository;
    private final ModelMapper modelMapper;
    private final IValidateErrorsDTO validateErrorsDTO;

    @Autowired
    public PublicationUserService(IPublicationUserRepository publicationUserRepository, ModelMapper modelMapper,
                                  IValidateErrorsDTO validateErrorsDTO) {
        this.publicationUserRepository = publicationUserRepository;
        this.modelMapper = modelMapper;
        this.validateErrorsDTO = validateErrorsDTO;
    }

    @Override
    public ResultService<List<PublicationUserDTO>> GetAllPublicationUser() {
        try {
            List<PublicationUserDTO> listPublic = publicationUserRepository.GetAllPublicationUser();

            if(listPublic == null){
                return ResultService.Fail("not found");
            }

            return ResultService.Ok(listPublic); // testar isso para ver se o "Mapper" vai funcionar
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PublicationUserDTO> GetPublicationByUserId(UUID userId) {
        try {
            PublicationUserDTO publication = publicationUserRepository.GetPublicationByUserId(userId);

            if(publication == null){
                return ResultService.Fail("not found");
            }

            return ResultService.Ok(publication); // testar isso para ver se o "Mapper" vai funcionar
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PublicationUserDTO> Create(PublicationUserCreateValidatorDTO publicationUserCreateValidatorDTO, BindingResult result) {

        if(publicationUserCreateValidatorDTO == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID publicationId = UUID.randomUUID();
            LocalDateTime now = LocalDateTime.now();
            var text = publicationUserCreateValidatorDTO.getText();
            var publicationImg = publicationUserCreateValidatorDTO.getPublicationImg();
            var userId = publicationUserCreateValidatorDTO.getUserId();

            PublicationUser publicationUser = new PublicationUser(publicationId, text, now, publicationImg, 0,userId, null);
//            PublicationUser(UUID id, String text, LocalDateTime publicationDate, String publicationImg, Integer commentsCount, UUID userId, User user)

            var createPublicationUser = publicationUserRepository.create(publicationUser);

            var userMap = modelMapper.map(createPublicationUser, PublicationUserDTO.class);

            return ResultService.Ok(userMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
