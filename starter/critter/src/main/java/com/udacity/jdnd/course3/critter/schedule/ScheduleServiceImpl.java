package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(List<Long> employeeIds, List<Long> petIds, Schedule schedule) {
        List<Employee> employeeList = employeeIds
                .stream()
                .map(id -> employeeService.getEmployeeById(id))
                .collect(Collectors.toList());

        List<Pet> pets = petIds
                .stream()
                .map(id -> petService.getPetById(id))
                .collect(Collectors.toList());
        schedule.setEmployees(employeeList);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleByPetId(Long petId) {
        Pet pet = petService.getPetById(petId);
        return scheduleRepository.findByPetsIn(Collections.singletonList(pet));
    }

    @Override
    public List<Schedule> getScheduleByEmployeeId(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return scheduleRepository.findByEmployeesIn(Collections.singletonList(employee));
    }

    @Override
    public List<Schedule> getScheduleByCustomerId(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return scheduleRepository.findByPetsIn(customer.getPets());
    }
}
