package uz.unicon.charityproject.service;

import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.HelpType;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.HelpTypeDto;
import uz.unicon.charityproject.repository.HelpTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HelpTypeService {

    final
    HelpTypeRepository helpTypeRepository;

    public HelpTypeService(HelpTypeRepository helpTypeRepository) {
        this.helpTypeRepository = helpTypeRepository;
    }


    public ApiResponse add(HelpTypeDto helpTypeDto) {

        boolean existsByName = helpTypeRepository.existsByName(helpTypeDto.getName());
        if (existsByName){
            return new ApiResponse("Bunday yordam turi mavjud",false);
        }

        HelpType helpType = new HelpType();
        helpType.setName(helpTypeDto.getName());
        HelpType save = helpTypeRepository.save(helpType);
        return new ApiResponse("Yordam turi qo'shildi",true,save);
    }

    public ApiResponse getAll(){
        List<HelpType> helpTypes = helpTypeRepository.findAll();
        for (HelpType helpType : helpTypes) {
            if (!helpType.isActive()){
                return new ApiResponse("Yordam turi topilmadi",false);
            }
        }
        return new ApiResponse("Barcha yordam turlari ro'yhati",true,helpTypes);
    }

    public ApiResponse get(Integer id) {
        Optional<HelpType> byId = helpTypeRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("Yordam turi topilmadi",false);
        }

        if (!byId.get().isActive()) {
            return new ApiResponse("Yordam turi topilmadi",false);
        }

        return new ApiResponse("Muvaffaqiyatli topildi",true,byId.get());
    }

    public ApiResponse edit(Integer id, HelpTypeDto helpTypeDto) {
        Optional<HelpType> byId = helpTypeRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("Yordam turi topilmadi",false);
        }
        HelpType editingHelpType = byId.get();
        editingHelpType.setName(helpTypeDto.getName());
        helpTypeRepository.save(editingHelpType);
        return new ApiResponse("Yordam turi tahrirlandi",true);
    }

    public ApiResponse delete(Integer id) {
        Optional<HelpType> byId = helpTypeRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("Yordam turi topilmadi",false);
        }
        HelpType deletingHelpType = byId.get();
        deletingHelpType.setActive(false);
        return new ApiResponse("Yordam turi ochirildi",true);
    }
}
