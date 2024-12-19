package com.app.vetClinicApp.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Указывает на то, что данный класс является сущностью для персистентности данных
@Entity

// Генерирует геттеры и сеттеры для полей класса
@Getter
@Setter

// Указывает на то, что данная сущность будет храниться в таблице с названием "users"
@Table(name = "users")
public class User implements Serializable {

    // Уникальный идентификатор пользователя
    @Id

    // Указывает на то, что поле "id" будет храниться в столбце с названием "user_id"
    @Column(name = "user_id")

    // Указывает на то, что значение поля "id" будет сгенерировано автоматически при сохранении сущности
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Имя пользователя
    private String username;

    // Email пользователя
    private String email;

    // Пароль пользователя (хранится в зашифрованном виде)
    private String password;

    // Флаг, указывающий на то, активен ли пользователь
    private boolean enable;

    // Связь "многие ко многим" с сущностью "Role"
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    // Указывает на то, что данная связь будет храниться в таблице с названием "users_roles"
    @JoinTable(name = "users_roles",

            // Столбец, который будет хранить внешний ключ на сущность "User"
            joinColumns = @JoinColumn(name = "user_id"),

            // Столбец, который будет хранить внешний ключ на сущность "Role"
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
