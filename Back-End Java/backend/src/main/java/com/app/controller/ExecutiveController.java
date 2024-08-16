package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.entities.Executive;
import com.app.services.ExecutiveServices;

@RestController
@RequestMapping("/executives")
@CrossOrigin(origins = "http://localhost:3000")
public class ExecutiveController {

    @Autowired
    private ExecutiveServices executiveServices;

    public ExecutiveController() {
        System.out.println("Inside Executive Controller Constructor " + getClass());
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerExecutive(@RequestBody Executive executive) {
        System.out.println("Inside Register Executive Function PostMapping");
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(executiveServices.registerExecutive(executive));
        } catch (Exception e) {
            System.err.println("Registration Error: " + e.getMessage());
            e.printStackTrace();  // Log the stack trace for better debugging
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                 .body("Executive Registration Failed: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Executive> checkLogin(@RequestBody String[] credentials) {
        System.out.println("Inside Check Login Function PostMapping");
        try {
           
            Executive executive = executiveServices.checkLogin(credentials[0], credentials[1]);
            if (executive != null) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(executive);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
