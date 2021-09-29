package com.io;

import com.api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.naming.CannotProceedException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopologyIO {

    /**
     * Reads a single topology from JSON file
     *
     * @param path as a String
     * @return topology as a Topology
     * @throws IOException if file is not found
     * @throws ParseException if file can't be parsed
     * @throws CannotProceedException if a new component type is given
     */
    public Topology readJSONTopology(final String path) throws IOException, CannotProceedException, ParseException {
        final BufferedReader file = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);
        final JSONParser jsonParser = new JSONParser();
        final JSONObject jsonTopology = (JSONObject) jsonParser.parse(file);

        file.close();
        return buildTopologyFromObject(jsonTopology);
    }

    private Topology buildTopologyFromObject(final JSONObject jsonTopology) throws CannotProceedException {
        final String topologyID = (String) jsonTopology.get("id");

        final JSONArray jsonComponents = (JSONArray) jsonTopology.get("components");
        final ArrayList<Component> components = new ArrayList<>();
        Map<String, String> netlist;

        for (final Object object : jsonComponents) {
            final JSONObject jsonComponent = (JSONObject) object;

            final String componentID = (String) jsonComponent.get("id");
            final String type = (String) jsonComponent.get("type");
            netlist = getNetlistFromObject(jsonComponent);
            final DeviceSpecs specs = getDeviceSpecsFromObject(jsonComponent);

            final Component component = new Component(componentID, type, netlist, specs);
            components.add(component);
        }

        return new Topology(topologyID, components);
    }

    private Map<String, String> getNetlistFromObject(final JSONObject jsonComponent) {
        final JSONObject jsonNetlist = (JSONObject) jsonComponent.get("netlist");
        final Map<String, String> netlist = new HashMap<>();
        jsonNetlist.keySet().forEach(key ->
        {
            final String value = (String) jsonNetlist.get(key);
            netlist.put((String) key, value);
        });
        return netlist;
    }

    private DeviceSpecs getDeviceSpecsFromObject(final JSONObject jsonComponent) throws CannotProceedException {
        JSONObject jsonSpecs;
        final String type = (String) jsonComponent.get("type");
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
                throw new CannotProceedException(type + " type isn't yet handled in the API");
        }
        final double defaultValue = ((Number) jsonSpecs.get("default")).doubleValue();
        specs.setDefaultValue(defaultValue);
        final double min = ((Number) jsonSpecs.get("max")).doubleValue();
        specs.setMin(min);
        final double max = ((Number) jsonSpecs.get("max")).doubleValue();
        specs.setMax(max);

        return specs;
    }

    /**
     * Writes a given topology to JSON file
     *
     * @param topology as a Topology
     * @throws FileNotFoundException if file is not found
     */
    public void writeJSONTopology(final Topology topology) throws FileNotFoundException {
        final JSONObject topologyObject = new JSONObject();

        topologyObject.put("id", topology.getId());

        final JSONArray componentsObject = new JSONArray();
        final List<Component> components = topology.getComponents();
        Map<String, Object> main = new LinkedHashMap<>();
        Map<String, java.io.Serializable> specs;
        Map<String, java.io.Serializable> netlist;
        for (final Component component : components) {
            main.clear();
            main.put("id", component.getId());
            main.put("type", component.getType());

            specs = getDeviceSpecsFromComponent(component);
            main.put(component.getSpecs().getName(), specs);

            netlist = getNetlistFromComponent(component);
            main.put("netlist", netlist);

            componentsObject.add(main);
        }
        topologyObject.put("components", componentsObject);
        final PrintWriter printWriter = new PrintWriter("/home/ta7a/IdeaProjects/TopologyAPI/src/resources/test.json");
        printWriter.write(topologyObject.toJSONString());
        printWriter.flush();
        printWriter.close();

    }

    private Map<String, java.io.Serializable> getDeviceSpecsFromComponent(final Component component) {
        final Map<String, java.io.Serializable> specs = new LinkedHashMap<>();
        specs.put("default", component.getSpecs().getDefaultValue());
        specs.put("min", component.getSpecs().getMin());
        specs.put("max", component.getSpecs().getMax());
        return specs;
    }

    private Map<String, java.io.Serializable> getNetlistFromComponent(final Component component) {
        return new LinkedHashMap<>(component.getNetlist());
    }
}

