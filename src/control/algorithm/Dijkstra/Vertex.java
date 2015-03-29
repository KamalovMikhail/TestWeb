package control.algorithm.Dijkstra;

/**
 * Created by mikhail on 21.03.15.
 */
public class Vertex {
    public char label;
    public boolean isInTree;

    public Vertex(char lab){
        label = lab;
        isInTree=false;
    }
}
