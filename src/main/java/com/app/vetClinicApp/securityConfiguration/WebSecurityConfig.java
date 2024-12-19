package com.app.vetClinicApp.securityConfiguration;

import com.app.vetClinicApp.serviceImp.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Указывает на то, что данный класс является конфигурационным классом для Spring Security
@Configuration
// Включает поддержку Spring Security в приложении
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Создание бина для сервиса, предоставляющего детали о пользователях (логины и пароли)
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    // Создание бина для шифрования паролей пользователей
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // Создание бина для аутентификации пользователей
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    // Настройка аутентификации пользователей
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // Настройка авторизации запросов
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/css/",
                "/images/",
                "/fonts/",
                "/scripts/",
        };

        http
                .authorizeRequests()
                // Разрешить доступ к главной странице всем пользователям
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
                // Разрешить доступ к странице управления пользователями только администраторам
                .antMatchers("/user/*, /userPage.html", "/user/listusers").hasAuthority("ADMIN")
                // Разрешить доступ к статическим ресурсам всем пользователям
                .antMatchers(staticResources).permitAll()
                // Для всех остальных запросов требовать аутентификацию
                .anyRequest().authenticated()
                .and()
                // Настройка формы входа в систему
                .formLogin()
                // Указать адрес страницы входа
                .loginPage("/login")
                // Указать параметр запроса, содержащий имя пользователя
                .usernameParameter("email")
                // Разрешить доступ к странице входа всем пользователям
                .permitAll()
                .and()
                // Настройка выхода из системы
                .logout().permitAll()
                .and()
                // Настройка обработки исключений
                .exceptionHandling()
                // Указать адрес страницы, отображаемой при отказе в доступе
                .accessDeniedPage("/403")
                ;
    }
}
