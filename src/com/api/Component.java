package com.api;


import java.util.Map;

public class Component {
    private transient String id;
    private transient String type;
    private transient Map<String, String> netlist;
    private transient DeviceSpecs specs;

    public Component() {
    }

    public Component(final String id, final String type, final Map<String, String> netlist, final DeviceSpecs specs) {
        this.id = id;
        this.type = type;
        this.netlist = netlist;
        this.specs = specs;
    }

    public Component(final Map<String, String> netlist) {
        this.netlist = netlist;
    }

    /**
     * Gets a map of component's terminals and their nodes
     *
     * @return netlist as a Map<String,String>
     */
    public Map<String, String> getNetlist() {
        return netlist;
    }

    /**
     * Gets component's specifications as a DeviceSpecs instance
     *
     * @return specs as a DeviceSpecs
     */
    public DeviceSpecs getSpecs() {
        return specs;
    }

    /**
     * Gets component's ID
     *
     * @return id as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Gets component's type
     *
     * @return type as a String
     */
    public String getType() {
        return type;
    }
}
