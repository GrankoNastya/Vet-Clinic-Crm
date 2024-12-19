package com.app.vetClinicApp.service;

import com.app.vetClinicApp.model.entity.PetOwner;
import java.util.List;
import java.util.Optional;

public interface IPetOwnerService {
    PetOwner save(PetOwner owner);
    PetOwner update(PetOwner owner);
    void deleteById(Long id);
    List<PetOwner> getAll();
    List<PetOwner> getByName(String name);
    PetOwner getById(Long id);
    List<PetOwner> findAllById(Long id);

}
