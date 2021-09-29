// Java program to read JSON from a file
package com;

import com.API.Topology;
import com.API.TopologyManager;
import com.IO.StreamIO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Topology topology = new Topology();
        TopologyManager topologyManager = new TopologyManager();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String line = "TOPOLOGY API\n Enter your operation\'s number:\n 1. Read\n 2. Write\n 3. Query Topologies\n 4. Delete Topology\n 5. Query Devices\n 6. Query Devices with Netlist Node\n 7. Quit";
        System.out.println(line);
        while (true) {
            char operation = myObj.next().charAt(0);
            myObj.nextLine();
            if (operation == '1') {
                System.out.println("Enter file path:");
                String filePath = myObj.nextLine();
                topology = StreamIO.readJSONTopology(filePath);
                topologyManager.addTopology(topology);
                System.out.println("Done!");
            } else if (operation == '2') {
                System.out.println("Enter topology ID:");
                String id = myObj.nextLine();
                topology = topologyManager.getTopology(id);
                StreamIO.writeJSONTopology(topology);
                System.out.println("Done!");
            } else if (operation == '3') {
                System.out.println(topologyManager.getTopologies());
            } else if (operation == '4') {
                System.out.println("Enter topology ID:");
                String id = myObj.nextLine();
                topologyManager.deleteTopology(id);
                System.out.println("Done!");
            } else if (operation == '5') {
                System.out.println("Enter topology ID:");
                String id = myObj.nextLine();
                topology = topologyManager.getTopology(id);
                System.out.println(topology.getComponents());
            } else if (operation == '6') {
                System.out.println("Enter topology ID:");
                String id = myObj.nextLine();
                topology = topologyManager.getTopology(id);
                System.out.println("Enter Netlist ID:");
                id = myObj.nextLine();
                System.out.println(topology.getComponentsWithNetListNode(id));
            } else
                System.exit(0);
        }
    }
}


