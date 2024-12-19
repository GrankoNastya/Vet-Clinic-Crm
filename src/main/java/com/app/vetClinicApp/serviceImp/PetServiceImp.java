package com.app.vetClinicApp.serviceImp;

import com.app.vetClinicApp.model.entity.Pet;
import com.app.vetClinicApp.model.entity.PetOwner;
import com.app.vetClinicApp.repository.IPetRepository;
import com.app.vetClinicApp.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImp implements IPetService {

    @Autowired
    private IPetRepository iPetRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Pet save(Pet pet) {
        return this.iPetRepository.save(pet);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        iPetRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pet> getAllPets() {
        return (List<Pet>) this.iPetRepository.findAll();
    }

    //get pets by "name" keyword
    @Transactional(readOnly = true)
    @Override
    public List<Pet> getPetsByName(String petname) {
        return this.iPetRepository.findPetsByNameIgnoreCase(petname);
    }

    @Override
    public List<Pet> findPetsByPetOwnerId(Long id) {
        return this.iPetRepository.findPetsByPetOwnerId(id);
    }

    @Override
    public Pet getById(Long id) {
        return iPetRepository.findById(id).orElse(null);
    }

}
