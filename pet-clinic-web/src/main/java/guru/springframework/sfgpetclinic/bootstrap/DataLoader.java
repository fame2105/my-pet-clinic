package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 7/25/18.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count > 0){
            loadData();    
        }
        
    }

    private void loadData() {
        PetType butterfly = new PetType();
        butterfly.setType("Butterfly");
        PetType savedButterflyPetType = petTypeService.save(butterfly);

        PetType dog = new PetType();
        dog.setType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);
//----------------------------------------------------------------
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

//-------------------------------------------------------------
        Owner aayushi = new Owner();
        aayushi.setFirstName("Aayushi");
        aayushi.setLastName("Pathak");

        Pet aayushisPet = new Pet();
        aayushisPet.setPetType(dog);
        aayushisPet.setName("Aayushi\'s dog");
        aayushisPet.setOwner(aayushi);
        aayushisPet.setBirthDate(LocalDate.now());

        aayushi.getPets().add(aayushisPet);
        ownerService.save(aayushi);


        Owner fame = new Owner();
        fame.setFirstName("Fame");
        fame.setLastName("Issrani");

        Pet famesPet = new Pet();
        famesPet.setPetType(savedButterflyPetType);
        famesPet.setName("fame\'s butterfly");
        famesPet.setBirthDate(LocalDate.now());
        famesPet.setOwner(fame);

        fame.getPets().add(famesPet);
        ownerService.save(fame);

        System.out.println("Loaded Owners....");
//----------------------------------------------------------------

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vet1.getSpecialities().add(dentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
