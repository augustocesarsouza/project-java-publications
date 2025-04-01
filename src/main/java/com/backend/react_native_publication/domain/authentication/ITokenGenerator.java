package com.backend.react_native_publication.domain.authentication;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.backend.react_native_publication.domain.InfoErrors.InfoErrors;
import com.backend.react_native_publication.domain.entities.User;
public interface ITokenGenerator{
    InfoErrors<TokenOutValue> generatorTokenUser(User user);
    Claim getClaimUserId(String token) throws TokenExpiredException;
}
