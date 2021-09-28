package com.IO;

import com.API.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StreamIO {
    public static Topology readJSONTopology(String path) throws Exception {
        Topology topology = new Topology();
        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            JSONObject jsonTopology = (JSONObject) obj;
            String id = (String) jsonTopology.get("id");
            topology.setId(id);
            JSONArray jsonComponents = (JSONArray) jsonTopology.get("components");

            for (Object o : jsonComponents) {
                JSONObject jsonComponent = (JSONObject) o;
                Component component = new Component();

                id = (String) jsonComponent.get("id");
                component.setId(id);

                String type = (String) jsonComponent.get("type");
                component.setType(type);

                JSONObject jsonNetlist = (JSONObject) jsonComponent.get("netlist");
                HashMap<String, String> netlist = new HashMap<>();
                jsonNetlist.keySet().forEach(keyStr ->
                {
                    Object keyvalue = jsonNetlist.get(keyStr);
                    netlist.put((String) keyStr, (String) keyvalue);
                });
                component.setNetlist(netlist);

                JSONObject jsonSpecs = null;
                DeviceSpecs specs = null;
                switch (type) {
                    case "resistor":
                        jsonSpecs = (JSONObject) jsonComponent.get("resistance");
                        specs = new ResistanceDeviceSpecs();
                        specs.setName("resistance");
                        break;
                    case "nmos":
                        jsonSpecs = (JSONObject) jsonComponent.get("m(l)");
                        specs = new TransistorDeviceSpecs();
                        specs.setName("m(l)");
                        break;
                }
                double default_value = ((Number) jsonSpecs.get("default")).doubleValue();
                double min = ((Number) jsonSpecs.get("max")).doubleValue();
                double max = ((Number) jsonSpecs.get("max")).doubleValue();
                specs.setDefault_value(default_value);
                specs.setMax(max);
                specs.setMin(min);
                component.setSpecs(specs);

                topology.addComponent(component);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return topology;
    }

    public static void writeJSONTopology(Topology topology)
            throws FileNotFoundException {
        // creating JSONObject
        JSONObject top = new JSONObject();

        // putting data to JSONObject
        top.put("id", topology.getId());

        JSONArray components = new JSONArray();
        ArrayList<Component> topologyComponents = topology.getComponents();
        for(Component component: topologyComponents) {
            Map main = new LinkedHashMap();
            main.put("id", component.getId());
            main.put("type", component.getType());
            Map specs = new LinkedHashMap();
            specs.put("default",component.getSpecs().getDefault_value());
            specs.put("min",component.getSpecs().getMin());
            specs.put("max",component.getSpecs().getMax());
            Map netlist = new LinkedHashMap();
            for (var entry : component.getNetlist().entrySet()) {
                netlist.put(entry.getKey(),entry.getValue());
            }
            main.put(component.getSpecs().getName(),specs);
            main.put("netlist",netlist);
            components.add(main);
        }
        top.put("components",components);

        // writing JSON to file:"JSONExample.json" in cwd
        PrintWriter pw = new PrintWriter("/home/ta7a/IdeaProjects/TopologyAPI/src/resources/JSONExample.json");
        pw.write(top.toJSONString());

        pw.flush();
        pw.close();
    }
}

