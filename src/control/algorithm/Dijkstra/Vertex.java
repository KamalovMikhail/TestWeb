package control.algorithm.Dijkstra;

/**
 * Created by mikhail on 21.03.15.
 */
public class Vertex {
    public String label;
    public boolean isInTree;

    public Vertex(String lab){
        label = lab;
        isInTree=false;
    }
}
