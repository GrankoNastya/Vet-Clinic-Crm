package com.app.vetClinicApp.controller;

import com.app.vetClinicApp.model.entity.Pet;
import com.app.vetClinicApp.model.entity.PetOwner;
import com.app.vetClinicApp.service.IPetOwnerService;
import com.app.vetClinicApp.service.IPetService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Getter
@Setter
public class PetController {

    @Autowired
    private IPetService iPetService;
    @Autowired
    private IPetOwnerService ownerService;

    // Сохранение питомца
    @PostMapping("/pet/save")
    public String savePet(@ModelAttribute("pet") Pet pet) {
        this.iPetService.save(pet); // Сохранение питомца в базе данных
        return "redirect:/pet/petlistpage";
    }

    // Открытие формы для добавления нового питомца
    @GetMapping("/pet/new")
    public String showNewPetForm(Model model) {
        // Создание атрибута модели для привязки данных формы
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        List<PetOwner> petOwnerList = ownerService.getAll();
        model.addAttribute("ownerList", petOwnerList);
        return "pet/newPetPage";
    }

    // Вывод списка питомцев в таблице
    @GetMapping("/pet/petlistpage")
    public String getPets(Model model) {
        model.addAttribute("listOfPets", iPetService.getAllPets());
        return "pet/petPage"; // Имя html-страницы
    }

    // Открытие формы "newOwnerPage" для редактирования
    @GetMapping("/pet/edit/{id}")
    public String showEditPetForm(@PathVariable("id") Long id, Model model) {
        Pet pet = iPetService.getById(id);
        model.addAttribute("pet", pet);
        List<PetOwner> petOwnerList = ownerService.getAll();
        model.addAttribute("ownerList", petOwnerList);
        return "pet/newPetPage";
    }

    // Вывод подробной информации о питомце (владельцах и некоторые столбцы) в таблице
    @GetMapping("/pet/petdetail/{id}")
    public String petDetail(@PathVariable("id") Long id, Model model) {
        Pet pet = iPetService.getById(id);
        model.addAttribute("pets", pet);

        if (pet.getPetOwner() == null) {
            return "pet/petDetailsNullPage";
        } else {
            return "pet/petDetailsPage"; // Имя html-страницы
        }
    }

    // Удаление питомца по идентификатору
    @GetMapping("/pet/delete/{id}")
    public String deletePet(@PathVariable("id") Long id, Model model) {
        iPetService.deleteById(id);
        return "redirect:/pet/petlistpage";
    }

    // Поиск питомца по имени
    @GetMapping("/pet/search")
    public String getByName(String name, Model model) {
        if (name != null) {
            model.addAttribute("listOfPets", iPetService.getPetsByName(name));
        } else {
            model.addAttribute("listOfPets", iPetService.getAllPets());
        }
        return "pet/searchPetPage";
    }
}
