package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Pet;

import java.util.List;

public interface PetService {

    Pet savePet(Pet pet, Long customerId);

    Pet getPetById(Long id);

    List<Pet> getAllPets();

    List<Pet> getPetsByOwnerId(Long ownerId);
}
