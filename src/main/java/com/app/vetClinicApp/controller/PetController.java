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

    //is the save method
    @PostMapping("/pet/save")
    public String savePet(@ModelAttribute("pet") Pet pet) {
        this.iPetService.save(pet); //save pet to dataBase
        return "redirect:/pet/petlistpage";
    }

    //opens the form page for adding a new pet
    @GetMapping("/pet/new")
    public String showNewPetForm(Model model) {
        //creating mode attribute to bind form data
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        List<PetOwner> petOwnerList = ownerService.getAll();
        model.addAttribute("ownerList", petOwnerList);
        return "pet/newPetPage";
    }

    //used to list Pets in a table
    @GetMapping("/pet/petlistpage")
    public String getPets(Model model) {
        model.addAttribute("listOfPets", iPetService.getAllPets());
        return "pet/petPage"; //name of html page
    }

    // used to open "newOwnerPage Form" for update
    @GetMapping("/pet/edit/{id}")
    public String showEditPetForm(@PathVariable("id") Long id, Model model) {
        Pet pet = iPetService.getById(id);
        model.addAttribute("pet", pet);
        List<PetOwner> petOwnerList = ownerService.getAll();
        model.addAttribute("ownerList", petOwnerList);
        return "pet/newPetPage";
    }

    //used to list Pet Details (owners and some columns) in table
    @GetMapping("/pet/petdetail/{id}")
    public String petDetail(@PathVariable("id") Long id, Model model) {
        Pet pet = iPetService.getById(id);
        model.addAttribute("pets", pet);

        if(pet.getPetOwner()==null){
            return "pet/petDetailsNullPage";
        }else{
            return "pet/petDetailsPage"; //name of html page
        }
    }

    //used to delete pet by id
    @GetMapping("/pet/delete/{id}")
    public String deletePet(@PathVariable("id") Long id, Model model) {
        iPetService.deleteById(id);
        return "redirect:/pet/petlistpage";

    }

    //used to search Pet by name
    @GetMapping("/pet/search")
    public String getByName(String name, Model model){
        if(name!=null){
            model.addAttribute("listOfPets",iPetService.getPetsByName(name));
        }else{
            model.addAttribute("listOfPets",iPetService.getAllPets());
        }
        return "pet/searchPetPage";
    }
}
