package com.codewithsanjay.springbootjpa.Service;

import com.codewithsanjay.springbootjpa.Respository.AppUserRepository;
import com.codewithsanjay.springbootjpa.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser login(AppUser user) {
        AppUser loggedInUser = appUserRepository
                .findUserByUserNameAndPassword(user.getUsername(), user.getPassword());
        return loggedInUser;
    }

    public AppUser register(AppUser appUser) {
        appUserRepository.save(appUser);
        return appUser;
    }
}
