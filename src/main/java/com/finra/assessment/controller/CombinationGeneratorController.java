package com.finra.assessment.controller;

import com.finra.assessment.service.MakeCombinations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "")
public class CombinationGeneratorController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{number}", produces = "application/json")
    public ResponseEntity<Object> getCombinations(@PathVariable String number)throws Exception{
        ResponseEntity<Object> res=(number.length()==7||number.length()==10)?new MakeCombinations().generateCombinations(number):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid!");
        return res;
    }
}