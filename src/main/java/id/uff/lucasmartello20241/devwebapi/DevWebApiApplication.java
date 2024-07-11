package id.uff.lucasmartello20241.devwebapi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.uff.lucasmartello20241.devwebapi.model.entities.Adoption;
import id.uff.lucasmartello20241.devwebapi.model.entities.Cart;
import id.uff.lucasmartello20241.devwebapi.model.entities.CartItem;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.model.enums.AdoptionStatus;
import id.uff.lucasmartello20241.devwebapi.model.enums.Role;
import id.uff.lucasmartello20241.devwebapi.repositories.AdoptionRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.CartItemRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.CartRepository;
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

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public void run(String... args) {

		User user1 = new User();
		user1.setName("Lucas Martello");
		user1.setEmail("lucas@gmail.com");
		user1.setUsername("Lucas");
		user1.setPassword(passwordEncoder.encode("lucas123"));
		user1.setRole(Role.ADMIN);
		user1.setAccountNonExpired(true);
		user1.setAccountNonLocked(true);
		user1.setCredentialsNonExpired(true);
		user1.setEnabled(true);

		User user2 = new User();
		user2.setName("Yasmim Martello");
		user2.setEmail("yasmim@gmail.com");
		user2.setUsername("Yasmim");
		user2.setPassword(passwordEncoder.encode("Yasmim123"));
		user2.setRole(Role.USER);
		user2.setAccountNonExpired(true);
		user2.setAccountNonLocked(true);
		user2.setCredentialsNonExpired(true);
		user2.setEnabled(true);
		
		userRepository.save(user1);
		userRepository.save(user2);

		Pet pet1 = new Pet("Maggie", "cachorro", 3 , 1.33, 44.56, 300.00);
		Pet pet2 = new Pet("Hazel", "cachorro", 2 , 1.28, 47.39, 250.00);
		Pet pet3 = new Pet("Salem", "gato", 1 , 0.34, 5.68, 120.50);
		Pet pet4 = new Pet("Bruce", "cachorro", 7 , 1.24, 15.87, 400.00);
		Pet pet5 = new Pet("Mac", "cachorro", 10 , 1.30, 50.87, 200.00);
		Pet pet6 = new Pet("Wanda", "gato", 4 , 0.68, 8.98, 167.70);
		Pet pet7 = new Pet("Leia", "gato", 3 , 0.68, 8.98, 167.70);
		Pet pet8 = new Pet("Mel", "cachorro", 10 , 1.30, 50.87, 200.00);
		Pet pet9 = new Pet("Maia", "gato", 4 , 0.68, 8.98, 167.70);
		Pet pet10 = new Pet("Luna", "gato", 3 , 0.68, 8.98, 167.70);

		petRepository.save(pet1);
		petRepository.save(pet2);
		petRepository.save(pet3);
		petRepository.save(pet4);
		petRepository.save(pet5);
		petRepository.save(pet6);
		petRepository.save(pet7);
		petRepository.save(pet8);
		petRepository.save(pet9);
		petRepository.save(pet10);
		
		Sanctuary sanctuary1 = new Sanctuary("lar dos pets", "Brasil", "Rio de Janeiro", "Niteroi", "Rua das flores 100", user1);
		Sanctuary sanctuary2 = new Sanctuary("casa dos pets", "Colombia", "São Paulo", "Niteroi", "Rua das árvores 50", user2);

		sanctuaryRepository.save(sanctuary1);
		sanctuaryRepository.save(sanctuary2);

		SanctuaryPet sanctuaryPet1 = new SanctuaryPet(pet1.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet2 = new SanctuaryPet(pet2.getId(), sanctuary1.getId(), AdoptionStatus.ADOPTED, "amigavel");
		SanctuaryPet sanctuaryPet3 = new SanctuaryPet(pet3.getId(), sanctuary2.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet4 = new SanctuaryPet(pet4.getId(), sanctuary2.getId(), AdoptionStatus.ADOPTED, "amigavel");
		SanctuaryPet sanctuaryPet5 = new SanctuaryPet(pet5.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet6 = new SanctuaryPet(pet6.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet7 = new SanctuaryPet(pet7.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet8 = new SanctuaryPet(pet8.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet9 = new SanctuaryPet(pet9.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");
		SanctuaryPet sanctuaryPet10 = new SanctuaryPet(pet10.getId(), sanctuary1.getId(), AdoptionStatus.WAITING_ADOPTION, "amigavel");

		sanctuaryPet1 = sanctuaryPetRepository.save(sanctuaryPet1);
		sanctuaryPet2 = sanctuaryPetRepository.save(sanctuaryPet2);
		sanctuaryPet3 = sanctuaryPetRepository.save(sanctuaryPet3);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet4);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet5);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet6);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet7);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet8);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet9);
		sanctuaryPet4 = sanctuaryPetRepository.save(sanctuaryPet10);

		Adoption adoption1 = new Adoption(user1.getId(), sanctuaryPet2.getId(), LocalDateTime.now());
		Adoption adoption2 = new Adoption(user2.getId(), sanctuaryPet4.getId(), LocalDateTime.now());

		adoptionRepository.save(adoption1);
		adoptionRepository.save(adoption2);

		Cart cart = new Cart(user1.getId());
		cartRepository.save(cart);

		CartItem ci1 = new CartItem(cart, sanctuaryPet1.getId(), 1);
		CartItem ci2 = new CartItem(cart, sanctuaryPet2.getId(), 1);

		ci1 = cartItemRepository.save(ci1);
		ci2 = cartItemRepository.save(ci2);
	}

}
