package com.app.vetClinicApp.serviceImp;

import com.app.vetClinicApp.model.entity.Role;
import com.app.vetClinicApp.repository.IRoleRepository;
import com.app.vetClinicApp.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements IRolesService {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }
}
