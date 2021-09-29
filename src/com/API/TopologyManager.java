package com.API;


import java.util.ArrayList;

public class TopologyManager {
    private final ArrayList<Topology> topologies = new ArrayList<>();

    public Topology getTopology(String id) throws Exception {
        try {
            int index = getTopologyIndex(id);
            return topologies.get(index);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteTopology(String id) throws Exception {
        try {
            int index = getTopologyIndex(id);
            topologies.remove(index);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private int getTopologyIndex(String id) throws Exception {
        for (int i = 0; i < topologies.size(); i++) {
            if (topologies.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new Exception("Couldn't find a topology with this ID");
    }

    public void addTopology(Topology topology) {
        topologies.add(topology);
    }

    public ArrayList<Topology> getTopologies() {
        return topologies;
    }
}
