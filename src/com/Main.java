// Java program to read JSON from a file
package com;

import com.API.Topology;
import com.API.TopologyManager;
import com.IO.TopologyIO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Topology topology;
        TopologyManager topologyManager = new TopologyManager();
        Scanner scanner = new Scanner(System.in);
        TopologyIO io = new TopologyIO();
        String operations = "1. Read\n 2. Write\n 3. Query Topologies\n 4. Delete Topology\n 5. Query Devices\n 6. Query Devices with Netlist Node\n 7. Quit";
        String intro = "TOPOLOGY API\n Enter your operation's number:\n " + operations;
        System.out.println(intro);
        while (true) {
            String operation = scanner.nextLine();
            try {
                switch (operation) {
                    case "1":
                        System.out.println("Enter file path:");
                        String filePath = scanner.nextLine();
                        topology = io.readJSONTopology(filePath);
                        topologyManager.addTopology(topology);
                        System.out.println("Done!");
                        break;
                    case "2":
                        System.out.println("Enter topology ID:");
                        String id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        io.writeJSONTopology(topology);
                        System.out.println("Done! You can find your topology.json file at /home/ta7a/IdeaProjects/TopologyAPI/src/resources");
                        break;
                    case "3":
                        topologyManager.getTopologies().forEach((top) -> System.out.println(top.getId()));
                        break;
                    case "4":
                        System.out.println("Enter topology ID:");
                        id = scanner.nextLine();
                        topologyManager.deleteTopology(id);
                        System.out.println("Done!");
                        break;
                    case "5":
                        System.out.println("Enter topology ID:");
                        id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        topology.getComponents().forEach((comp) -> System.out.println(comp.getId()));
                        break;
                    case "6":
                        System.out.println("Enter topology ID:");
                        id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        System.out.println("Enter Netlist ID:");
                        id = scanner.nextLine();
                        topology.getComponentsWithNetListNode(id).forEach((comp) -> System.out.println(comp.getId()));
                        break;
                    case "7":
                        System.out.println("Thank you");
                        System.exit(0);
                    default:
                        System.out.println("Please choose from the specified operations\n " + operations);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


