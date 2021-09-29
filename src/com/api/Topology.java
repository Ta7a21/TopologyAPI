package com.api;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Topology {
    private transient String id;
    private transient List<Component> components = new ArrayList<>();

    public Topology() {
    }

    public Topology(final String id, final List<Component> components) {
        this.id = id;
        this.components = components;
    }

    public Topology(final String id) {
        this.id = id;
    }

    /**
     * Gets topology's id
     *
     * @return id as a String
     */
    public String getId() {
        return id;
    }


    /**
     * Gets topology's components
     *
     * @return components as a List<Component>
     * @throws NoSuchElementException if there are no components
     */
    public List<Component> getComponents() {
        if(components.isEmpty())
        {
            throw new NoSuchElementException("Couldn't find components in this topology");
        }
        return components;
    }

    /**
     * Gets topology's components connected to a given netlist node
     *
     * @param netlistID as a String
     * @return components as a List<Component>
     * @throws NoSuchElementException if topology or netlist node aren't found
     */
    public List<Component> getComponentsWithNetListNode(final String netlistID){
        final List<Component> components = new ArrayList<>();
        for (final Component component : this.components) {
            for (final String nodeID : component.getNetlist().values()) {
                if (nodeID.equals(netlistID)) {
                    components.add(component);
                }
            }
        }
        if (components.isEmpty()) {
            throw new NoSuchElementException("Couldn't find components connected to this node");
        }
        return components;
    }
}
