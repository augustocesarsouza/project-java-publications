package com.backend.react_native_publication.application.dto.validateErrosDTOs;

import com.backend.react_native_publication.application.services.ErrorValidation;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface IValidateErrorsDTO {
    List<ErrorValidation> ValidateDTO(List<ObjectError> errorsDTO);
}
