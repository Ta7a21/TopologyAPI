// Java program to read JSON from a file
package com;

import com.API.Topology;
import com.IO.StreamIO;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Topology topology = new Topology();
        try {
            topology = StreamIO.readJSONTopology("/home/ta7a/IdeaProjects/TopologyAPI/src/resources/topology.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StreamIO.writeJSONTopology(topology);

    }
}
