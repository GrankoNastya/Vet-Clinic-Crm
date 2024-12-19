package com.app.vetClinicApp.serviceImp;

import com.app.vetClinicApp.model.entity.PetOwner;
import com.app.vetClinicApp.repository.IPetOwnerRepository;
import com.app.vetClinicApp.service.IPetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetOwnerServiceImp implements IPetOwnerService {

    @Autowired
    private IPetOwnerRepository iPetOwnerRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PetOwner save(PetOwner owner) {
        return this.iPetOwnerRepository.save(owner);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PetOwner update(PetOwner owner) {
        return this.iPetOwnerRepository.save(owner);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        this.iPetOwnerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PetOwner> getAll() {
        return this.iPetOwnerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PetOwner> getByName(String name) {
        return this.iPetOwnerRepository.findPetOwnersByNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    @Override
    public PetOwner getById(Long id){
        return iPetOwnerRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PetOwner> findAllById(Long id){
        return iPetOwnerRepository.findAllById(Collections.singleton(id));
    }




}

