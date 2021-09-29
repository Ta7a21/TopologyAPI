// Java program to read JSON from a file
package com.main;

import com.api.Topology;
import com.api.TopologyManager;
import com.io.TopologyIO;
import org.json.simple.parser.ParseException;

import javax.naming.CannotProceedException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TopologyCLI {
    void start() {
        Topology topology;
        final TopologyManager topologyManager = new TopologyManager();
        final Scanner scanner = new Scanner(System.in);
        final TopologyIO io = new TopologyIO();
        final String operations = "1. Read\n 2. Write\n 3. Query Topologies\n 4. Delete Topology\n 5. Query Devices\n 6. Query Devices with Netlist Node\n 7. Quit";
        final String intro = "TOPOLOGY API\n Enter your operation's number:\n " + operations;
        System.out.println(intro);
        while (true) {
            final String operation = scanner.nextLine();
            try {
                switch (operation) {
                    case "1":
                        System.out.println("Enter file path:");
                        final String filePath = scanner.nextLine();
                        topology = io.readJSONTopology(filePath);
                        topologyManager.addTopology(topology);
                        System.out.println("Done!");
                        break;
                    case "2":
                        inputID();
                        String id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        System.out.println(topology.getClass());
                        io.writeJSONTopology(topology);
                        System.out.println("Done! You can find your topology.json file at /home/ta7a/IdeaProjects/TopologyAPI/src/resources");
                        break;
                    case "3":
                        topologyManager.getTopologies().forEach((top) -> System.out.println(top.getId()));
                        break;
                    case "4":
                        inputID();
                        id = scanner.nextLine();
                        topologyManager.deleteTopology(id);
                        System.out.println("Done!");
                        break;
                    case "5":
                        inputID();
                        id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        topology.getComponents().forEach((comp) -> System.out.println(comp.getId()));
                        break;
                    case "6":
                        inputID();
                        id = scanner.nextLine();
                        topology = topologyManager.getTopology(id);
                        System.out.println("Enter Netlist ID:");
                        id = scanner.nextLine();
                        topology.getComponentsWithNetListNode(id).forEach((comp) -> System.out.println(comp.getId()));
                        break;
                    default:
                        System.out.println("Please choose from the specified operations\n " + operations);
                        break;
                }
            } catch (CannotProceedException | IOException | ParseException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void inputID(){
        System.out.println("Enter topology ID:");
    }

    public static void main(final String[] args) {
        new TopologyCLI().start();
    }
}


