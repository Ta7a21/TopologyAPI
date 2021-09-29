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

public class TopologyIO {
    public Topology readJSONTopology(String path) throws Exception {
        try {
            FileReader file = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonTopology = (JSONObject) jsonParser.parse(file);

            return buildTopologyFromObject(jsonTopology);
        } catch (FileNotFoundException ex) {
            throw new Exception("File not found");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Topology buildTopologyFromObject(JSONObject jsonTopology) throws Exception {
        String topologyID = (String) jsonTopology.get("id");

        JSONArray jsonComponents = (JSONArray) jsonTopology.get("components");
        ArrayList<Component> components = new ArrayList<>();
        for (Object object : jsonComponents) {
            JSONObject jsonComponent = (JSONObject) object;

            String componentID = (String) jsonComponent.get("id");
            String type = (String) jsonComponent.get("type");
            HashMap<String, String> netlist = getNetlistFromObject(jsonComponent);
            DeviceSpecs specs = getDeviceSpecsFromObject(jsonComponent);

            Component component = new Component(componentID, type, netlist, specs);
            components.add(component);
        }

        return new Topology(topologyID, components);
    }

    private HashMap<String, String> getNetlistFromObject(JSONObject jsonComponent) {
        JSONObject jsonNetlist = (JSONObject) jsonComponent.get("netlist");
        HashMap<String, String> netlist = new HashMap<>();
        jsonNetlist.keySet().forEach(key ->
        {
            String value = (String) jsonNetlist.get(key);
            netlist.put((String) key, value);
        });
        return netlist;
    }

    private DeviceSpecs getDeviceSpecsFromObject(JSONObject jsonComponent) throws Exception {
        JSONObject jsonSpecs;
        String type = (String) jsonComponent.get("type");
        DeviceSpecs specs;
        switch (type) {
            case "resistor":
                jsonSpecs = (JSONObject) jsonComponent.get("resistance");
                specs = new ResistanceDeviceSpecs("resistance");
                break;
            case "nmos":
                jsonSpecs = (JSONObject) jsonComponent.get("m(l)");
                specs = new TransistorDeviceSpecs("m(l)");
                break;
            default:
                throw new Exception(type + " type isn't yet handled in the API");
        }
        double default_value = ((Number) jsonSpecs.get("default")).doubleValue();
        specs.setDefault_value(default_value);
        double min = ((Number) jsonSpecs.get("max")).doubleValue();
        specs.setMin(min);
        double max = ((Number) jsonSpecs.get("max")).doubleValue();
        specs.setMax(max);

        return specs;
    }

    public void writeJSONTopology(Topology topology)
            throws Exception {
        JSONObject topologyObject = new JSONObject();

        topologyObject.put("id", topology.getId());

        JSONArray componentsObject = new JSONArray();
        ArrayList<Component> components = topology.getComponents();
        for (Component component : components) {
            Map<String, Object> main = new LinkedHashMap<>();
            main.put("id", component.getId());
            main.put("type", component.getType());

            Map<String, java.io.Serializable> specs = getDeviceSpecsFromComponent(component);
            main.put(component.getSpecs().getName(), specs);

            Map<String, java.io.Serializable> netlist = getNetlistFromComponent(component);
            main.put("netlist", netlist);

            componentsObject.add(main);
        }
        topologyObject.put("components", componentsObject);

        try {
            PrintWriter pw = new PrintWriter("/home/ta7a/IdeaProjects/TopologyAPI/src/resources/topology.json");
            pw.write(topologyObject.toJSONString());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Map<String, java.io.Serializable> getDeviceSpecsFromComponent(Component component) {
        Map<String, java.io.Serializable> specs = new LinkedHashMap<>();
        specs.put("default", component.getSpecs().getDefault_value());
        specs.put("min", component.getSpecs().getMin());
        specs.put("max", component.getSpecs().getMax());
        return specs;
    }

    private Map<String, java.io.Serializable> getNetlistFromComponent(Component component) {
        return new LinkedHashMap<>(component.getNetlist());
    }
}

