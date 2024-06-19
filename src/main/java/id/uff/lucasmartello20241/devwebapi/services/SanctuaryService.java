package id.uff.lucasmartello20241.devwebapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryWithOwnerNameDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;
import id.uff.lucasmartello20241.devwebapi.utils.Utils;

@Service
public class SanctuaryService {
    private final SanctuaryRepository sanctuaryRepository;
    private final UserRepository userRepository;

    @Autowired
    public SanctuaryService(SanctuaryRepository sanctuaryRepository, UserRepository userRepository) {
        this.sanctuaryRepository = sanctuaryRepository;
        this.userRepository = userRepository;
    }

    public Sanctuary create(Sanctuary sanctuary) {
        return sanctuaryRepository.save(sanctuary);
    }

    public Sanctuary createByOwnerName(SanctuaryWithOwnerNameDTO sanctuaryWithOwnerName){
        Optional<User> user = userRepository.findByName(sanctuaryWithOwnerName.ownerName());
        User owner;

        if (user.isPresent()){
            owner = user.get();
        } else {
            owner = new User(sanctuaryWithOwnerName.ownerName(), Utils.createGenericEmail(sanctuaryWithOwnerName.ownerName()), "12345");
            owner = userRepository.save(owner);
        }

        Sanctuary sanctuary = new Sanctuary(
            sanctuaryWithOwnerName.name(),
            sanctuaryWithOwnerName.country(),
            sanctuaryWithOwnerName.state(),
            sanctuaryWithOwnerName.city(),
            sanctuaryWithOwnerName.address(),
            owner
        );

        return sanctuaryRepository.save(sanctuary);
    }

    public Sanctuary read(int id) throws NotFoundException {
        return sanctuaryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", id)));
    }

    public Sanctuary update(Sanctuary sanctuary) throws NotFoundException {
        sanctuaryRepository.findById(sanctuary.getId()).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", sanctuary.getId())));
        return sanctuaryRepository.save(sanctuary);
    }

    public void delete(int id) throws NotFoundException {
        sanctuaryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", id)));
        sanctuaryRepository.deleteById(id);
    }

    public Page<Sanctuary> findAllPaginated(Pageable pageable) {
        return sanctuaryRepository.findAllPaginated(pageable);
    }

    public Page<Sanctuary> findBySearchValuePaginated(String searchValue, Pageable pageable) {
        return sanctuaryRepository.findBySearchValuePaginated(searchValue, pageable);
    }
}
