package com.API;

import java.util.HashMap;

public class Component {
    private String id;
    private String type;
    private HashMap<String,String> netlist = new HashMap<>();
    private DeviceSpecs specs;

    public HashMap<String, String> getNetlist() {
        return netlist;
    }

    public void setNetlist(HashMap<String,String> netlist) {
        this.netlist = netlist;
    }

    public void setId(String id) {

        this.id = id;
    }

    public DeviceSpecs getSpecs() {
        return specs;
    }

    public void setSpecs(DeviceSpecs specs) {
        this.specs = specs;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


// "resistance"->Component

// Component {Enum Device}

// Device{String resistance,String m(1)}

