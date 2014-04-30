import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class RoadReconstruction {

    ArrayList<Road> properRoads = new ArrayList<Road>(), brokenRoads = new ArrayList<Road>();
    Hashtable<String, Integer> citiesLookUpTable  = new Hashtable<String, Integer>();
    ArrayList<City> cities = new ArrayList<City>();
    int citiesCount = 0;

    public String selectReconstruction(String[] roads) {
        for(String road : roads) {
            String[] tokens = road.split(" ");
            if(tokens.length == 3) {
                properRoads.add(new Road(tokens[0], tokens[1], tokens[2], -1));
            }
            else {
                brokenRoads.add(new Road(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])));
            }
        }

        Collections.sort(brokenRoads);

        buildSpanningTree();

        ArrayList<String> roadsToBuild = new ArrayList<String>();

        for(Road brokenRoad : brokenRoads) {

            if(!citiesLookUpTable.containsKey(brokenRoad.from)) {
                citiesLookUpTable.put(brokenRoad.from, citiesCount);
                cities.add(new City(brokenRoad.from));
                citiesCount++;
            }

            if(!citiesLookUpTable.containsKey(brokenRoad.to)) {
                citiesLookUpTable.put(brokenRoad.to, citiesCount);
                cities.add(new City(brokenRoad.to));
                citiesCount++;
            }
            City sourceRoot = getRootCity(brokenRoad.from);
            City targetRoot = getRootCity(brokenRoad.to);

            if(sourceRoot != targetRoot) {
                targetRoot.parent = sourceRoot;
                roadsToBuild.add(brokenRoad.id);
            }
        }

        int connectedComponents = 0;
        for(City city : cities) {
            if(city.parent == null) {
                connectedComponents++;
            }
        }

        if(connectedComponents > 1) {
            return "IMPOSSIBLE";
        }

        Collections.sort(roadsToBuild);

        String result = "";
        for(String road : roadsToBuild) {
            result += road+" " ;
        }

        return result.trim();

    }

    private City getRootCity(String from) {
        City rootCity = cities.get(citiesLookUpTable.get(from));
        while(rootCity.parent != null) {
            rootCity = rootCity.parent;
        }

        return rootCity;
    }

    private void buildSpanningTree() {
        for(Road road : properRoads) {
            if(!citiesLookUpTable.containsKey(road.from)) {
                citiesLookUpTable.put(road.from, citiesCount);
                cities.add(new City(road.from));
                citiesCount++;
            }
            if(!citiesLookUpTable.containsKey(road.to)) {
                citiesLookUpTable.put(road.to, citiesCount);
                cities.add(new City(road.to));
                citiesCount++;
            }

            City sourceRoot = getRootCity(road.from);
            City targetRoot = getRootCity(road.to);

            if(sourceRoot != targetRoot) {
                targetRoot.parent = sourceRoot;
            }
        }
    }

    public class Road implements Comparable<Road>{
        String id, from, to;
        double costOfRepair;

        Road(String id, String from, String to, double costOfRepair) {
            this.id = id;
            this.from = from;
            this.to = to;
            this.costOfRepair = costOfRepair;
        }

        @Override
        public int compareTo(Road road) {
            if(this.costOfRepair < road.costOfRepair) {
                return -1;
            }
            else if(this.costOfRepair > road.costOfRepair) {
                return 1;
            }

            return this.id.compareTo(road.id);
        }
    }

    public class City {
        String name;
        City parent;

        City(String name) {
            this.name = name;
            this.parent = null;
        }
    }
}
