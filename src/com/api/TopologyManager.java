package com.api;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TopologyManager {
    private List<Topology> topologies = new ArrayList<>();

    /**
     * Gets topology by its ID
     *
     * @param id as a String
     * @return topology as Topology
     * @throws NoSuchElementException if topology is not found
     */
    public Topology getTopology(final String id){
            final int index = getTopologyIndex(id);
            return topologies.get(index);
    }

    /**
     * Deletes topology by its ID
     *
     * @param id as a String
     * @throws NoSuchElementException if topology is not found
     */
    public void deleteTopology(final String id){
            final int index = getTopologyIndex(id);

            topologies.remove(index);
    }

    private int getTopologyIndex(final String id) {
        for (int i = 0; i < topologies.size(); i++) {
            if (topologies.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new NoSuchElementException("Couldn't find a topology with this ID");
    }

    /**
     * Adds a given topology to the stored topology list
     *
     * @param topology as a Topology
     */
    public void addTopology(final Topology topology) {
        topologies.add(topology);
    }

    /**
     * Gets stored topologies
     *
     * @return topologies as List<Topology>
     * @throws NoSuchElementException if there are no topologies stored
     */
    public List<Topology> getTopologies() {
        if(topologies.isEmpty())
        {
            throw new NoSuchElementException("Couldn't find any stored topologies");
        }
        return topologies;
    }
}
