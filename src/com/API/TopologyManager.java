package com.API;


import java.util.ArrayList;

public class TopologyManager {
    private ArrayList<Topology> topologies = new ArrayList<>();

    public void deleteTopology(String id) {
        for (int i = 0; i < topologies.size(); i++) {
            if (topologies.get(i).getId().equals(id)) {
                topologies.remove(i);
            }
        }
    }

    public void addTopology(Topology topology) {
        topologies.add(topology);
    }

    public ArrayList<Topology> getTopologies() {
        return topologies;
    }
}
