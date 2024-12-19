package com.app.vetClinicApp.service;

import com.app.vetClinicApp.model.entity.User;
import java.util.List;

public interface IUsersService {
    User saveUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
