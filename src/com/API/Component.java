package com.API;

import java.util.HashMap;

public class Component {
    private final String id;
    private final String type;
    private final HashMap<String, String> netlist;
    private final DeviceSpecs specs;

    public Component(String id, String type, HashMap<String, String> netlist, DeviceSpecs specs) {
        this.id = id;
        this.type = type;
        this.netlist = netlist;
        this.specs = specs;
    }

    public HashMap<String, String> getNetlist() {
        return netlist;
    }

    public DeviceSpecs getSpecs() {
        return specs;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
