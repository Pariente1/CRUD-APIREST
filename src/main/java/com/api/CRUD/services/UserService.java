package com.api.CRUD.services;

import com.api.CRUD.models.UserModel;
import com.api.CRUD.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository IUserRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) IUserRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return IUserRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return IUserRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        Optional<UserModel> userOptional = IUserRepository.findById(id);
        if(userOptional.isPresent()) {
            UserModel user = userOptional.get();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());

            return IUserRepository.save(user);
        } else {
            throw new RuntimeException("User with id " + id + " not found");
        }
    }

    public Boolean deleteUser(Long id){
        try{
            IUserRepository.deleteById(id);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
