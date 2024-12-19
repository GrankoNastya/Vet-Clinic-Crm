package com.app.vetClinicApp.serviceImp;

import com.app.vetClinicApp.model.entity.User;
import com.app.vetClinicApp.repository.IUserRepository;
import com.app.vetClinicApp.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Указывает на то, что данный класс является сервисным классом (реализует бизнес-логику приложения)
@Service
public class UsersServiceImp implements IUsersService {

    // Автоматическое внедрение зависимости на репозиторий пользователей
    @Autowired
    private IUserRepository userRepository;

    // Указывает на то, что метод должен выполняться в рамках транзакции
    @Transactional
    @Override
    public User saveUser(User user) {
        // Сохранение пользователя в базе данных
        return userRepository.save(user);
    }

    // Указывает на то, что метод должен выполняться в рамках транзакции только для чтения
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        // Получение списка всех пользователей из базы данных
        return (List<User>) this.userRepository.findAll();
    }

    // Указывает на то, что метод должен выполняться в рамках транзакции
    @Transactional
    @Override
    public void deleteUser(Long id) {
        // Удаление пользователя из базы данных по его идентификатору
        this.userRepository.deleteById(id);
    }
}
