package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Executive;
import com.app.dao.ExecutiveDAO;

@Service
public class ExecutiveServiceImpl implements ExecutiveServices {

    @Autowired
    private ExecutiveDAO executiveDAO;

    @Override
    public String registerExecutive(Executive executive) {
        if (executive.getEname() == null || executive.getPassword() == null) {
            throw new IllegalArgumentException("Name and Password must not be null");
        }

        // Optionally check if the executive already exists
        if (executiveDAO.findByEname(executive.getEname()) != null) {
            throw new IllegalArgumentException("Executive with this name already exists");
        }

        // Save the executive with plain text password
        executiveDAO.save(executive);
        return "Executive Registered Successfully";
    }

    @Override
    public Executive checkLogin(String eid, String password) {
        Executive executive = executiveDAO.findByEid(eid);

        if (executive == null) {
            throw new IllegalArgumentException("Invalid Executive ID or Password");
        }

        // Compare plain text passwords
        if (executive.getPassword().equals(password)) {
            return executive;
        } else {
            throw new IllegalArgumentException("Invalid Executive ID or Password");
        }
    }
}
