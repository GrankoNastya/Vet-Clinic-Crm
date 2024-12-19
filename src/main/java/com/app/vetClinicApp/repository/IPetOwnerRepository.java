package com.app.vetClinicApp.repository;

import com.app.vetClinicApp.model.entity.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetOwnerRepository extends JpaRepository<PetOwner, Long> {


    List<PetOwner> findPetOwnersByNameIgnoreCase(@Param("name") String name);

}

