package com.app.vetClinicApp.service;

import com.app.vetClinicApp.model.entity.Pet;
import org.springframework.stereotype.Component;


import java.util.List;

public interface IPetService {
    Pet save(Pet pet);
    void deleteById(Long id);
    List<Pet> getAllPets();
    List<Pet> getPetsByName(String petname);
    List<Pet> findPetsByPetOwnerId(Long id);
    Pet getById(Long id);


}
