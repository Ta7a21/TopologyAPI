package com.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TopologyTest {
    List<Component> components = new ArrayList<>();
    Topology topology = new Topology("top1",components);

    @Test
    void getComponents() {
        components.add(new Component());
        assert topology.getComponents().size()==1;
    }

    @Test
    void getComponentsWithNetListNode() throws Exception{
        components.add(new Component(Map.of("key1", "n1")));
        assert topology.getComponentsWithNetListNode("n1").size()==1;
    }
}