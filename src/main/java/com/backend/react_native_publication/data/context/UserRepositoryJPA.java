package com.backend.react_native_publication.data.context;

import com.backend.react_native_publication.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, UUID> {

    @Query("SELECT new com.backend.react_native_publication.domain.entities." +
            "User(x.Id, x.Name, x.Email, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Id = :id")
    User GetUserById(UUID id);
    @Query("SELECT new com.backend.react_native_publication.domain.entities." +
            "User(x.Id, x.Name, x.Email, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetUserByEmailInfoUpdate(String email);
    @Query("SELECT new com.backend.react_native_publication.domain.entities." +
            "User(null, null, x.Email, x.PasswordHash, null, null) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User GetUserByIdInfoEmailPasswordHash(UUID userId);
    @Query("SELECT new com.backend.react_native_publication.domain.entities." +
            "User(x.Id, x.Name, x.Email, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetUserInfoToLogin(String email);

    @Query("SELECT new com.backend.react_native_publication.domain.entities." +
            "User(x.Id, null, x.Email, x.PasswordHash, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetUserByEmailToUserAuthentication(String email);
}

//User(UUID id, String name, String email, String passwordHash, Short confirmEmail, String userImage)