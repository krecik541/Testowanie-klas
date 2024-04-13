package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MageRepository {
    private Map<String, Mage> mageMap = new HashMap<>();

    public Optional<Mage> find(String name) {
        return Optional.ofNullable(mageMap.get(name));
    }

    public Optional<Mage> delete(String name) {
        if (mageMap.containsKey(name)) {
            return Optional.of(mageMap.remove(name));
        } else {
            throw new IllegalArgumentException("Object with name " + name + " does not exist.");
        }
    }

    public String save(Mage mage) {
        if (mageMap.containsKey(mage.getName())) {
            throw new IllegalArgumentException("Mage with this name already exists");
        }
        mageMap.put(mage.getName(), mage);
        return "done";
    }
}
