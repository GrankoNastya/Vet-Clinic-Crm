package com.app.vetClinicApp.serviceImp;

import com.app.vetClinicApp.model.entity.MyUserDetails;
import com.app.vetClinicApp.model.entity.User;
import com.app.vetClinicApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*
*
* This class use for just Login operations
*
* */
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUsersByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}
