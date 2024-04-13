package org.example;

import java.util.Collection;
import java.util.Optional;
public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> foundMage = repository.find(name);
        if (foundMage.isPresent()) {
            return foundMage.get().toString();
        } else {
            return "not found";
        }
    }

    public String delete(String name) {
        Optional<Mage> foundMage = repository.find(name);
        if (foundMage.isPresent()) {
            repository.delete(name);
            return "done";
        } else {
            return "not found";
        }
    }

    public String save(String name, int level) {
        if(repository.find(name).isPresent())
        {
            return "bad request";
        }
        else
        {
            repository.save(new Mage(name, level));
            return "done";
        }
    }
}