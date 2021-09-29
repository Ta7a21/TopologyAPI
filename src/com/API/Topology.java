package com.API;

import java.util.ArrayList;

public class Topology {
    private final String id;
    private final ArrayList<Component> components;

    public Topology(String id, ArrayList<Component> components) {
        this.id = id;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public ArrayList<Component> getComponentsWithNetListNode(String netlistID) throws Exception {
        ArrayList<Component> components = new ArrayList<>();
        for (Component component : this.components) {
            for (String nodeID : component.getNetlist().values()) {
                if (nodeID.equals(netlistID))
                    components.add(component);
            }
        }
        if (components.isEmpty()) throw new Exception("Couldn't find components connected to this node");
        return components;
    }
}
