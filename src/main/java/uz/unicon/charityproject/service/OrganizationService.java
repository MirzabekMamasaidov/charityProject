package uz.unicon.charityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.Organization;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.OrganizationDto;
import uz.unicon.charityproject.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;


    public ApiResponse add(OrganizationDto organizationDto) {
        Optional<Organization> optionalOrganization = organizationRepository.findByName(organizationDto.getName());
        if (optionalOrganization.isPresent()) {
            return new ApiResponse("Bunday nomli tashkilot bor",false);
        }
        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setActive(true);
        Organization savedOrganization = organizationRepository.save(organization);
        return new ApiResponse("Muvaffaqiyatli qo'shildi",true,savedOrganization);

    }

    public ApiResponse getAll() {
        List<Organization> organizationList = organizationRepository.findAll();
        return new ApiResponse("Tashkilotlar ro'yhati",true,organizationList);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        if (optionalOrganization.isEmpty()) {
            return new ApiResponse("Tashkilot topilmadi",false);
        }
        Organization organization = optionalOrganization.get();
        return new ApiResponse("Tashkilot topildi",true,organization);
    }

    public ApiResponse edit(Integer id, OrganizationDto organizationDto) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        if (optionalOrganization.isEmpty()) {
            return new ApiResponse("Tashkilot topilmadi",false);
        }
        Organization editingOrganization = optionalOrganization.get();
        editingOrganization.setName(organizationDto.getName());
        Organization editedOrganization = organizationRepository.save(editingOrganization);
        return new ApiResponse("O'zagartirildi",true,editedOrganization);
    }

    public ApiResponse delete(Integer id) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        if (optionalOrganization.isEmpty()) {
            return new ApiResponse("Tashkilot topilmadi",false);
        }
        Organization deletedOrganization = optionalOrganization.get();
        deletedOrganization.setActive(false);
        return new ApiResponse("Muvaffaqiyatli o'chirildi",true);
    }
}
