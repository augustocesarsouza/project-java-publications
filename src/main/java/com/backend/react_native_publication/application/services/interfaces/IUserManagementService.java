package com.backend.react_native_publication.application.services.interfaces;

import com.backend.react_native_publication.application.dto.UserDTO;
import com.backend.react_native_publication.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IUserManagementService {
    ResultService<UserDTO> findById(String phone);
    ResultService<UserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
//    ResultService<UserPasswordUpdateDTO> ChangePasswordUser(UserChangePasswordDTO userChangePasswordDTO, BindingResult resultValid);
//    ResultService<UserDTO> UpdateUserAll(UserUpdateAllDTOValidator userUpdateAllDTOValidator, BindingResult resultValid);
//    ResultService<UserDTO> UpdateCpfAndBirthDayUser(UserUpdateFillDTOValidator userUpdateFillDTOValidator, BindingResult resultValid);
//    ResultService<UserDTO> DeleteUser(UUID userID);
}
