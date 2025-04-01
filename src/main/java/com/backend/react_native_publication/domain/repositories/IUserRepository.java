package com.backend.react_native_publication.domain.repositories;

import com.backend.react_native_publication.domain.entities.User;

import java.util.UUID;

public interface IUserRepository {
    User GetUserById(UUID id);
    User GetUserByEmailInfoUpdate(String email);
    User GetUserByIdInfoEmailPasswordHash(UUID guidId);
    User GetUserInfoToLogin(String email);
    User GetUserByEmailToUserAuthentication(String email);
    User create(User user);
    User update(User user);
    User delete(UUID userId);
}
