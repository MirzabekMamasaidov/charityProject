package uz.unicon.charityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.repository.OrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;


}
