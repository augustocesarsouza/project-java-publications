package com.backend.react_native_publication.data.repositories;

import com.backend.react_native_publication.data.context.UserRepositoryJPA;
import com.backend.react_native_publication.domain.entities.User;
import com.backend.react_native_publication.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepository implements IUserRepository {
    private final UserRepositoryJPA userRepositoryJPA;
    @Autowired
    public UserRepository(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public User GetUserById(UUID id) {
        return userRepositoryJPA.GetUserById(id);
    }

    @Override
    public User GetUserByEmailInfoUpdate(String email) {
        return userRepositoryJPA.GetUserByEmailInfoUpdate(email);
    }

    @Override
    public User GetUserByIdInfoEmailPasswordHash(UUID guidId) {
        return userRepositoryJPA.GetUserByIdInfoEmailPasswordHash(guidId);
        //@Query("SELECT u.Email, u.PasswordHash FROM User AS u WHERE u.Id = :userId")
    }

    @Override
    public User GetUserInfoToLogin(String email) {
        return userRepositoryJPA.GetUserInfoToLogin(email);
    }

    @Override
    public User GetUserByEmailToUserAuthentication(String phone) {
        return userRepositoryJPA.GetUserByEmailToUserAuthentication(phone);
    }

    @Override
    public User create(User user) {
        if(user == null)
            return null;

        return userRepositoryJPA.save(user);
    }

    @Override
    public User update(User user) {
        if(user == null)
            return null;

        User userUpdate = userRepositoryJPA.findById(user.getId()).orElse(null);

        if(userUpdate == null)
            return null;

        userUpdate.setValuesUser(user.getName(), user.getEmail(), user.getPasswordHash(), user.getConfirmEmail(),
                user.getUserImage());

        return userRepositoryJPA.save(userUpdate);
    }

    @Override
    public User delete(UUID userId) {
        if(userId == null)
            return null;

        User user = userRepositoryJPA.findById(userId).orElse(null);

        if(user == null)
            return null;

        userRepositoryJPA.delete(user);

        return user;
    }
}
