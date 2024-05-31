package id.uff.lucasmartello20241.devwebapi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import id.uff.lucasmartello20241.devwebapi.model.entities.Adoption;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.model.enums.AdoptionStatus;
import id.uff.lucasmartello20241.devwebapi.repositories.AdoptionRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.PetRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryPetRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;

@SpringBootApplication
public class DevWebApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DevWebApiApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	SanctuaryRepository sanctuaryRepository;

	@Autowired
	PetRepository petRepository;

	@Autowired
	SanctuaryPetRepository sanctuaryPetRepository;

	@Autowired
	AdoptionRepository adoptionRepository;


	@Override
	public void run(String... args) {
		User user1 = new User("Lucas", "lucas@gmail.com", "abc123");
		User user2 = new User("Yasmim", "yasmim@gmail.com", "abc123");
		
		userRepository.save(user1);
		userRepository.save(user2);

		Pet pet1 = new Pet("Maggie", "cachorro", 3 , 1.33, 44.56);
		Pet pet2 = new Pet("Hazel", "cachorro", 2 , 1.28, 47.39);
		Pet pet3 = new Pet("Salem", "gato", 1 , 0.34, 5.68);
		Pet pet4 = new Pet("Bruce", "cachorro", 7 , 1.24, 15.87);

		petRepository.save(pet1);
		petRepository.save(pet2);
		petRepository.save(pet3);
		petRepository.save(pet4);

		Sanctuary sanctuary1 = new Sanctuary("lar dos pets", "Brasil", "Rio de Janeiro", "Niteroi", "Rua das flores 100", user1);
		Sanctuary sanctuary2 = new Sanctuary("lar dos pets 2", "Brasil", "Rio de Janeiro", "Niteroi", "Rua das flores 100", user2);

		sanctuaryRepository.save(sanctuary1);
		sanctuaryRepository.save(sanctuary2);

		SanctuaryPet sanctuaryPet1 = new SanctuaryPet(pet1.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet2 = new SanctuaryPet(pet2.getId(), sanctuary1.getId(), AdoptionStatus.ADOPTED, "amigavel");
		SanctuaryPet sanctuaryPet3 = new SanctuaryPet(pet3.getId(), sanctuary2.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet4 = new SanctuaryPet(pet4.getId(), sanctuary2.getId(), AdoptionStatus.ADOPTED, "amigavel");

		sanctuaryPetRepository.save(sanctuaryPet1);
		sanctuaryPetRepository.save(sanctuaryPet2);
		sanctuaryPetRepository.save(sanctuaryPet3);
		sanctuaryPetRepository.save(sanctuaryPet4);

		Adoption adoption1 = new Adoption(user1.getId(), sanctuaryPet2.getId(), LocalDateTime.now());
		Adoption adoption2 = new Adoption(user2.getId(), sanctuaryPet4.getId(), LocalDateTime.now());

		adoptionRepository.save(adoption1);
		adoptionRepository.save(adoption2);

	}

}
