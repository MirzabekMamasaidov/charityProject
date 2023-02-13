package uz.unicon.charityproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.entity.HelpType;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.HelpTypeDto;
import uz.unicon.charityproject.service.HelpTypeService;

import java.util.Optional;

@RestController
@RequestMapping("/api/helpType")
public class HelpTypeController {

    final
    HelpTypeService helpTypeService;

    public HelpTypeController(HelpTypeService helpTypeService) {
        this.helpTypeService = helpTypeService;
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody HelpTypeDto helpTypeDto){
        ApiResponse response = helpTypeService.add(helpTypeDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse all = helpTypeService.getAll();
        return ResponseEntity.status(all.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id){

        ApiResponse response = helpTypeService.get(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody HelpTypeDto helpTypeDto){
        ApiResponse response = helpTypeService.edit(id, helpTypeDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = helpTypeService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

}
