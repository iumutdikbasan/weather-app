package com.iumutdikbasan.weatherapp.service.concretes;

import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.general.BaseAdditionalFields;
import com.iumutdikbasan.weatherapp.general.BaseEntityService;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.repository.UserRepository;
import com.iumutdikbasan.weatherapp.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl extends BaseEntityService<User, UserRepository> implements UserService {


    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public User save(User user) {
        BaseAdditionalFields baseAdditionalFields = user.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
        }

        if (user.getId()  == null){
            baseAdditionalFields.setCreatedDate(LocalDateTime.now());
            baseAdditionalFields.setCreatedBy(user.getUsername());
        }


        baseAdditionalFields.setUpdatedDate(LocalDateTime.now());

        baseAdditionalFields.setUpdatedBy(user.getUsername());

        user.setBaseAdditionalFields(baseAdditionalFields);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }



    @Override
    public List<User> getUsersPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<User> users = userRepository.findAll(pageable).getContent();
        if(users.size() > 0){
            return users;
        }else {
            throw new MyException("Kullanıcı bulunamadı");
        }

    }

    @Override
    public List<User> getByUsernameStartsWith(String username) {
        List<User> users = userRepository.getByUsernameStartsWith(username);
        if(users.size() > 0){
            return users;
        }else {
            throw new MyException(username + " ile başlayan kullanıcı bulunamadı");
        }

    }


    @Override
    public List<User> getByUsernameContaining(String username) {
        List<User> users = userRepository.getByUsernameContaining(username);
        if(users.size() > 0){
            return users;
        }else {
            throw new MyException(username + " içeren kullanıcı bulunamadı");
        }
    }

    @Override
    public List<User> getAllByOrderByUsernameAsc(){
        List<User> users = userRepository.getAllByOrderByUsernameAsc();
        if(users.size() > 0){
            return users;
        }else {
            throw new MyException("Kullanıcı bulunamadı");
        }
    }
}