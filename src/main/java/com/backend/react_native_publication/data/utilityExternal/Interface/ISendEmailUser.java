package com.backend.react_native_publication.data.utilityExternal.Interface;


import com.backend.react_native_publication.domain.InfoErrors.InfoErrors;
import com.backend.react_native_publication.domain.entities.User;

public interface ISendEmailUser {
    InfoErrors<String> sendCodeRandom(User user, int code);
//    InfoErrors<String> sendEmailConfirmRegisterUser(User user);
}
