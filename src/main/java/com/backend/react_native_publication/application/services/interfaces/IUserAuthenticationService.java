package com.backend.react_native_publication.application.services.interfaces;

import com.backend.react_native_publication.application.dto.UserLoginDTO;
import com.backend.react_native_publication.application.services.ResultService;

public interface IUserAuthenticationService {
//    ResultService<CodeSendEmailUserValidatorDTO> SendCodeEmail(CodeSendEmailUserValidatorDTO codeSendEmailUserValidatorDTO, BindingResult result);
    ResultService<UserLoginDTO> Login(String email, String password);
//    ResultService<UserLoginDTO> VerifyPasswordUser(String phone,String password);
}
