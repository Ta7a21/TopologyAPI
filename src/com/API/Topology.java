package com.API;

import java.util.ArrayList;

public class Topology {
    private String id;
    private ArrayList<Component> components = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public ArrayList<Component> getComponentsWithNetListNode(String netlistID) {
        ArrayList<Component> components = new ArrayList<Component>();
        for (Component component : this.components) {
            for (var entry : component.getNetlist().entrySet()) {
                if(entry.getValue().equals(netlistID))
                    components.add(component);
            }
        }
        return components;
    }
}
