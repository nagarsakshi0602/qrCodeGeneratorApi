package com.example.springdataDemo.service;


import com.example.springdataDemo.model.User;
import com.example.springdataDemo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllPersons() {
        return (List<User>) userRepository.findAll();
    }
    public List<User> findByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
    public void deletePerson(Long personId) {
        userRepository.deleteById(personId);
    }
    public boolean addPerson(User user) {
        return userRepository.save(user) != null;
    }
    public boolean updatePerson(User user) {
        return userRepository.save(user) != null;
    }
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
