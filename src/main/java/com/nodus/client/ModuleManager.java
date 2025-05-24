package com.nodus.client;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();

    public static void register(Module module) {
        modules.add(module);
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static Module getByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
