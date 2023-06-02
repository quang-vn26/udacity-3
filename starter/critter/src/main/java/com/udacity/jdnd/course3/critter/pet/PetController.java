package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.common.MapperDTO;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = MapperDTO.convertToPet(petDTO);
        return MapperDTO.convertToPetDto(petService.savePet(pet, petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return MapperDTO.convertToPetDto(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return MapperDTO.convertToPetDtoList(petService.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return MapperDTO.convertToPetDtoList(petService.getPetsByOwnerId(ownerId));
    }
}
