package com.nodus.client;

public abstract class Module {
    protected String name;
    protected String description;
    protected boolean enabled;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.enabled = false;
    }

    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
    public void toggle() { enabled = !enabled; if (enabled) onEnable(); else onDisable(); }
    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onTick();
}
