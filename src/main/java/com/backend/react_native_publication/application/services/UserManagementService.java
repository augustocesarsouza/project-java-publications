package com.backend.react_native_publication.application.services;

import com.backend.react_native_publication.application.dto.UserDTO;
import com.backend.react_native_publication.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.react_native_publication.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.interfaces.IUserManagementService;
import com.backend.react_native_publication.application.util.interfaces.IBCryptPasswordEncoderUtil;
import com.backend.react_native_publication.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.react_native_publication.domain.entities.User;
import com.backend.react_native_publication.domain.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final IBCryptPasswordEncoderUtil bCryptPasswordEncoder;
    private final ICloudinaryUti cloudinaryUti;
    private final ModelMapper modelMapper;

    public UserManagementService(IUserRepository userRepository, IValidateErrorsDTO validateErrorsDTO,
                                 IBCryptPasswordEncoderUtil bCryptPasswordEncoder, ICloudinaryUti cloudinaryUti,
                                 ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryUti = cloudinaryUti;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<UserDTO> findById(String userId) {
        try {
            User user = userRepository.GetUserById(UUID.fromString(userId));

            if(user == null){
                return ResultService.Fail("not found");
            }

            var userMap = modelMapper.map(user, UserDTO.class);
            return ResultService.Ok(userMap); // testar isso para ver se o "Mapper" vai funcionar
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result) {
        if(userCreateValidatorDTO == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        User userCreate = new User();

        // VER SE VAI COLOCAR AQUI QUANDO CRIAR ENVIAR UM EMAIL PARA A CONTA ANTES DE CRIAR

        try {
            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userCreateValidatorDTO.getPassword());
            UUID uuid_user_id = UUID.randomUUID();

            if(userCreateValidatorDTO.getBase64ImageUser() != null){

                var resultCreate = cloudinaryUti.CreateMedia(userCreateValidatorDTO.getBase64ImageUser(), "img-user", 320, 320);

                if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                {
                    return ResultService.Fail("error when create ImgPerfil");
                }

                userCreate = new User(uuid_user_id, userCreateValidatorDTO.getName(), userCreateValidatorDTO.getEmail(),
                        passwordEncoder, (short)0, resultCreate.getImgUrl());

            }else {
                userCreate = new User(uuid_user_id, userCreateValidatorDTO.getName(), userCreateValidatorDTO.getEmail(),
                        passwordEncoder, (short)0, null);
            }

            var userData = userRepository.create(userCreate);

            var userMap = modelMapper.map(userData, UserDTO.class);

            return ResultService.Ok(userMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
