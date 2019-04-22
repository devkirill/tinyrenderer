import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WavefrontObj
{
    private final List<Vertex> vertices;
    private final List<Polygon> polygons;

    public WavefrontObj(List<Vertex> vertices, List<Polygon> polygons)
    {
        this.vertices = vertices;
        this.polygons = polygons;
    }

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public List<Polygon> getPolygons()
    {
        return polygons;
    }

    public static WavefrontObj parse(String fileName)
    {
        BufferedReader file;
        try
        {
            file = new BufferedReader(new FileReader(fileName));
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }

        List<List<String>> splitFileLines = file.lines()
                .map(line -> Arrays.asList(line.split(" +")))
                .collect(Collectors.toList());

        List<Vertex> vertices = splitFileLines.stream()
                .filter(l -> l.get(0).equals("v"))
                .map(l -> new Vertex(Double.valueOf(l.get(1)), Double.valueOf(l.get(2)), Double.valueOf(l.get(3))))
                .collect(Collectors.toList());

        List<Polygon> polygons = splitFileLines.stream()
                .filter(l -> l.get(0).equals("f"))
                .map(l -> l.stream()
                        .skip(1)
                        .map(s -> Arrays.asList(s.split("/")).get(0))
                        .map(Integer::valueOf)
                        .map(v -> v - 1)
                        .collect(Collectors.toList()))
                .map(Polygon::new)
                .collect(Collectors.toList());

        return new WavefrontObj(vertices, polygons);
    }

    public static class Polygon
    {
        private final List<Integer> polygon;

        public Polygon(List<Integer> polygon)
        {
            this.polygon = polygon;
        }

        public Integer size()
        {
            return polygon.size();
        }

        public Integer get(int node)
        {
            return polygon.get(node % polygon.size());
        }

        public List<Integer> getPolygon()
        {
            return polygon;
        }
    }
}
