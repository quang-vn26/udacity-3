package com.udacity.jdnd.course3.critter.services;


import com.udacity.jdnd.course3.critter.common.NotFoundException;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public Pet savePet(Pet pet, Long customerId) {
        Customer owner = customerService.getCustomerById(customerId);
        pet.setCustomer(owner);
        pet = petRepository.save(pet);

        List<Pet> petList = owner.getPets();
        if (petList == null) {
            petList = new ArrayList<>();
        }
        if (!petList.contains(pet)) {
            petList.add(pet);
            owner.setPets(petList);
            customerService.saveCustomer(owner);
        }
        return pet;
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find pet with id: " + id));
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findPetsByCustomerId(ownerId);
    }
}
