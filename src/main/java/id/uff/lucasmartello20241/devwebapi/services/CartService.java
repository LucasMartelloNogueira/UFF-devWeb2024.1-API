package id.uff.lucasmartello20241.devwebapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.dtos.CartWithPetsInfoDTO;
import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryPetWithPetInfoDTO;
import id.uff.lucasmartello20241.devwebapi.model.dtos.UpdateCartDTO;
import id.uff.lucasmartello20241.devwebapi.model.dtos.UserSimplifiedDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.Cart;
import id.uff.lucasmartello20241.devwebapi.model.entities.CartItem;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.repositories.CartItemRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.CartRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.PetRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryPetRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final SanctuaryPetRepository sanctuaryPetRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
            SanctuaryPetRepository sanctuaryPetRepository, UserRepository userRepository, PetRepository petRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.sanctuaryPetRepository = sanctuaryPetRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public CartWithPetsInfoDTO read(int id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("cart of id %d does not exist", id)));
        UserSimplifiedDTO user = UserSimplifiedDTO.fromEntity(userRepository.findById(cart.getUserId()).orElseThrow(() -> new NotFoundException(String.format("cart user of id %d does not exist", cart.getUserId()))));

        List<SanctuaryPetWithPetInfoDTO> sanctuaryPets = new ArrayList<>();
        
        for (CartItem cartItem : cart.getItems()) {
            SanctuaryPet sanctuaryPet = sanctuaryPetRepository.findById(cartItem.getSanctuaryPetId()).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d does not exist", cartItem.getSanctuaryPetId())));
            Pet pet = petRepository.findById(sanctuaryPet.getPetId()).orElseThrow(() -> new NotFoundException(String.format("Pet of id %d not found", sanctuaryPet.getPetId())));
            SanctuaryPetWithPetInfoDTO sanctuaryPetWithPetInfoDTO = SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet);
            sanctuaryPets.add(sanctuaryPetWithPetInfoDTO);
        }

        CartWithPetsInfoDTO cartWithPetsInfoDTO = new CartWithPetsInfoDTO(cart.getId(), user, sanctuaryPets);
        return cartWithPetsInfoDTO;
    }

    public CartWithPetsInfoDTO addItems(UpdateCartDTO updateCartDTO) throws NotFoundException {

        Optional<Cart> _cart = cartRepository.findById(updateCartDTO.cartId());

        if (!_cart.isPresent()) {
            throw new NotFoundException(String.format("cart of id %d does not exist", updateCartDTO.cartId()));
        }

        Cart cart = _cart.get();
        UserSimplifiedDTO user = UserSimplifiedDTO.fromEntity(userRepository.findById(cart.getUserId()).orElseThrow(() -> new NotFoundException(String.format("cart user of id %d does not exist", cart.getUserId()))));
        List<SanctuaryPetWithPetInfoDTO> sanctuaryPets = new ArrayList<>();

        // [2]
        // int sanctuaryPetId : updateCartDTO.sanctuaryPetsId()
        for (int sanctuaryPetId : updateCartDTO.sanctuaryPetsId()) {

            // [1]
            for (CartItem cartItem : cart.getItems()) {

                // tentou adicionar um item no carrinho que já está adicionado
                if (cartItem.getSanctuaryPetId() == sanctuaryPetId){
                    continue;
                } else {
                    // endpoint deve retornar na lista os itens que já estavam no carrinho
                    SanctuaryPet sanctuaryPet = sanctuaryPetRepository.findById(cartItem.getSanctuaryPetId()).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", cartItem.getSanctuaryPetId())));
                    Pet pet = petRepository.findById(sanctuaryPet.getPetId()).orElseThrow(() -> new NotFoundException(String.format("Pet of id %d not found", sanctuaryPet.getPetId())));
                    SanctuaryPetWithPetInfoDTO sanctuaryPetWithPetInfoDTO = SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet);
                    sanctuaryPets.add(sanctuaryPetWithPetInfoDTO);
                }
            }

            CartItem newCartItem = new CartItem(cart, sanctuaryPetId, 1);
            cartItemRepository.save(newCartItem);

            SanctuaryPet sanctuaryPet = sanctuaryPetRepository.findById(newCartItem.getSanctuaryPetId()).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", newCartItem.getSanctuaryPetId())));
            Pet pet = petRepository.findById(sanctuaryPet.getPetId()).orElseThrow(() -> new NotFoundException(String.format("Pet of id %d not found", sanctuaryPet.getPetId())));
            SanctuaryPetWithPetInfoDTO sanctuaryPetWithPetInfoDTO = SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet);
            sanctuaryPets.add(sanctuaryPetWithPetInfoDTO);

        }

        CartWithPetsInfoDTO cartWithPetsInfoDTO = new CartWithPetsInfoDTO(cart.getId(), user, sanctuaryPets);
        return cartWithPetsInfoDTO;

    }

    public CartWithPetsInfoDTO removeItems(UpdateCartDTO updateCartDTO) {
        Optional<Cart> cart = cartRepository.findById(updateCartDTO.cartId());

        if (!cart.isPresent()) {
            throw new NotFoundException(String.format("cart of id %d does not exist", updateCartDTO.cartId()));
        }

        UserSimplifiedDTO user = UserSimplifiedDTO.fromEntity(userRepository.findById(cart.get().getUserId()).orElseThrow(() -> new NotFoundException(String.format("cart user of id %d does not exist", cart.get().getUserId()))));
        List<SanctuaryPetWithPetInfoDTO> sanctuaryPets = new ArrayList<>();
        
        for (CartItem ci : cart.get().getItems()) {
            for (int sanctuaryPetId : updateCartDTO.sanctuaryPetsId()) {
                
                if (ci.getSanctuaryPetId() == sanctuaryPetId) {
                    cartItemRepository.deleteById(sanctuaryPetId);
                }else {
                    SanctuaryPet sanctuaryPet = sanctuaryPetRepository.findById(ci.getSanctuaryPetId()).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", ci.getSanctuaryPetId())));
                    Pet pet = petRepository.findById(sanctuaryPet.getPetId()).orElseThrow(() -> new NotFoundException(String.format("Pet of id %d not found", sanctuaryPet.getPetId())));
                    SanctuaryPetWithPetInfoDTO sanctuaryPetWithPetInfoDTO = SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet);
                    sanctuaryPets.add(sanctuaryPetWithPetInfoDTO);
                }
                
            }
        }

        CartWithPetsInfoDTO cartWithPetsInfoDTO = new CartWithPetsInfoDTO(cart.get().getId(), user, sanctuaryPets);
        return cartWithPetsInfoDTO;
    }

}
