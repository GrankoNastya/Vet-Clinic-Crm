import com.app.vetClinicApp.model.entity.Role;
import com.app.vetClinicApp.model.entity.User;
import com.app.vetClinicApp.service.IRolesService;
import com.app.vetClinicApp.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUsersService userService;

    @Autowired
    private IRolesService rolesService;

    // Получение списка всех пользователей
    @GetMapping("/user/listusers")
    public String getAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        if (userList == null) {
            model.addAttribute("error", "Пользователи не найдены");
            return "error";
        }
        model.addAttribute("users", userList);
        return "user/userPage";
    }

    // Удаление пользователя по идентификатору
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        try {
            userService.deleteUser(id);
            return "redirect:/user/listusers";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при удалении пользователя: " + e.getMessage());
            return "error";
        }
    }

    // Сохранение пользователя
    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        try {
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                model.addAttribute("error", "Имя пользователя не может быть пустым");
                return "error";
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                model.addAttribute("error", "Пароль не может быть пустым");
                return "error";
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);

            this.userService.saveUser(user); // Сохранение пользователя в базу данных
            return "redirect:/user/listusers";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при сохранении пользователя: " + e.getMessage());
            return "error";
        }
    }

    // Открытие формы для добавления нового пользователя
    @GetMapping("/user/new")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = this.rolesService.getAll();
        model.addAttribute("roleList", roles);
        return "user/newUserPage";
    }

}
