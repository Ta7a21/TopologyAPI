package com.io;

import com.api.Component;
import com.api.DeviceSpecs;
import com.api.Topology;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TopologyIOTest {
    String path = "/home/ta7a/IdeaProjects/TopologyAPI/src/resources/topology.json";
    TopologyIO io = new TopologyIO();
    @Test
    void readJSONTopology() throws Exception {
        Topology topology = io.readJSONTopology(path);
        assert topology != null;
    }

    @Test
    void writeJSONTopology() throws Exception{
        List<Component> components = new ArrayList<>();
        components.add(new Component("1","type",new HashMap<>(),new DeviceSpecs()));
        io.writeJSONTopology(new Topology("top1",components));
    }
}