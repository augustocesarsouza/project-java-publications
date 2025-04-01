package com.backend.react_native_publication.application.services;

import com.backend.react_native_publication.application.dto.UserDTO;
import com.backend.react_native_publication.application.dto.UserLoginDTO;
import com.backend.react_native_publication.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.react_native_publication.application.services.interfaces.IUserAuthenticationService;
import com.backend.react_native_publication.application.util.interfaces.IDictionaryCode;
import com.backend.react_native_publication.data.utilityExternal.Interface.ISendEmailUser;
import com.backend.react_native_publication.data.utilityExternal.Interface.ISendSmsTwilio;
import com.backend.react_native_publication.domain.InfoErrors.InfoErrors;
import com.backend.react_native_publication.domain.authentication.ITokenGenerator;
import com.backend.react_native_publication.domain.authentication.TokenOutValue;
import com.backend.react_native_publication.domain.entities.User;
import com.backend.react_native_publication.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ISendEmailUser sendEmailUser;
    private final ISendSmsTwilio sendSmsTwilio;
    private final IDictionaryCode dictionaryCode;
    private final AuthenticationManager authenticationManager;
    private final ITokenGenerator tokenGenerator;
    private final IValidateErrorsDTO validateErrorsDTO;

    @Autowired
    public UserAuthenticationService(IUserRepository userRepository, ModelMapper modelMapper, ISendEmailUser sendEmailUser, ISendSmsTwilio sendSmsTwilio,
                                     IDictionaryCode dictionaryCode, AuthenticationManager authenticationManager, ITokenGenerator tokenGenerator,
                                     IValidateErrorsDTO validateErrorsDTO) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.sendEmailUser = sendEmailUser;
        this.sendSmsTwilio = sendSmsTwilio;
        this.dictionaryCode = dictionaryCode;
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.validateErrorsDTO = validateErrorsDTO;
    }

    @Override
    @Transactional
    public ResultService<UserLoginDTO> Login(String email, String password) {
        var userLoginDTO = new UserLoginDTO();

        try {
            User user = userRepository.GetUserInfoToLogin(email);

            userLoginDTO.setPasswordIsCorrect(false);
            userLoginDTO.setMessage("Error user info login is null");

            if (user == null) return ResultService.Fail(userLoginDTO);

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User userAuth = (User) authenticate.getPrincipal();

            InfoErrors<TokenOutValue> tokenOut = tokenGenerator.generatorTokenUser(userAuth);

            if(!tokenOut.IsSuccess)
                return ResultService.Fail(tokenOut.Message);

            int randomCode = generateRandomNumber();
            dictionaryCode.putKeyValueDictionary(userAuth.getId().toString(), randomCode);
            //InfoErrors<String> resultSendCodeEmail = sendEmailUser.sendCodeRandom(userAuth, randomCode);

            userAuth.setName(user.getName());
            userAuth.setPasswordHash(null);
            var userDTO = modelMapper.map(userAuth, UserDTO.class);

            if(userDTO == null)
                return ResultService.Fail("error in null class mapping");

            userDTO.setToken(tokenOut.Data.getAccess_Token());

            userLoginDTO.setPasswordIsCorrect(true);
            userLoginDTO.setUserDTO(userDTO);

            return ResultService.Ok(userLoginDTO);
        } catch (Exception ex) {
            userLoginDTO.setPasswordIsCorrect(false);
            userLoginDTO.setMessage(ex.getMessage());
            return ResultService.Fail(userLoginDTO);
        }
    }
    private static int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
