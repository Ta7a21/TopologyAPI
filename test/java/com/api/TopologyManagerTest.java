package com.api;

import org.junit.jupiter.api.Test;

class TopologyManagerTest {

    TopologyManager topologyManager = new TopologyManager();

    @Test
    void deleteTopology(){
        topologyManager.addTopology(new Topology("top1"));
        topologyManager.addTopology(new Topology("top2"));
        topologyManager.deleteTopology("top1");
        assert topologyManager.getTopologies().size()==1;
    }

    @Test
    void getTopologies() {
        topologyManager.addTopology(new Topology());
        assert topologyManager.getTopologies().size()==1;
    }
}