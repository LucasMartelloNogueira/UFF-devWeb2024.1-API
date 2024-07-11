package id.uff.lucasmartello20241.devwebapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User read(int id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("user of id %d not found", id)));
    }

    public User update(User user) throws NotFoundException {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(String.format("user of id %d not found", user.getId())));

        return userRepository.save(user);
    }

    public void delete(int id) throws NotFoundException {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("user of id %d not found", id)));

        userRepository.deleteById(id);
    }

    public Page<User> findAllPaginated(Pageable pageable) {
        return userRepository.findAllPaginated(pageable);
    }

    public UserDetails findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
