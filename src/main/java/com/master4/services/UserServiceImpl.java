package com.master4.services;

import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
    }

    @Override
    public void save(User User) {

        userRepository.save(User);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByIdWithTags(long id) {
        return null;
    }

    @Override
    public User findByUserUsername(String username) {
        User u = null;
        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            return null;
        }
        for (int i=0; i<users.size();i++){
            if(users.get(i).getUsername().equals(username)==true){
                u =  users.get(i);
            }
        }
        return u;
    }
}
