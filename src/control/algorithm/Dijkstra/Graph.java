package control.algorithm.Dijkstra;

/**
 * Created by mikhail on 21.03.15.
 */
public class Graph {
    private final int INFINITY = 1000000;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int nTree;
    private DistPar sPath[];
    private int currentVert;
    private int startToCurrent;

    public Graph(int MAX_VERTS){
        vertexList = new Vertex[MAX_VERTS];
        adjMat= new int[MAX_VERTS][MAX_VERTS];
        nVerts=0;
        nTree=0;
        for (int j=0;j<MAX_VERTS;j++)
            for (int k=0;k<MAX_VERTS;k++)
                adjMat[j][k]=INFINITY;
        sPath = new DistPar[MAX_VERTS];

    }
    public void addVertex (char lab){
        vertexList[nVerts++] = new Vertex(lab);

    }

    public void addEdge(int start,int end,int weight){
        adjMat[start][end] = weight;
    }

    public void path(){
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree=1;
        for (int j=0;j<nVerts;j++){
            int tempDist = adjMat[startTree][j];
            sPath[j]= new DistPar(startTree,tempDist);
        }
        while(nTree<nVerts){
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist==INFINITY){
                System.out.println("Unreachable");
                break;
            }
            else
            {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
            displayPath();
            nTree = 0;
            for (int j=0;j<nVerts;j++){
                vertexList[j].isInTree=false;
            }

    }

    public int getMin(){
        int minDist = INFINITY;
        int indexMin = 0;
        for (int j=1;j<nVerts;j++){
            if (!vertexList[j].isInTree && sPath[j].distance<minDist)
            {
                minDist=sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    public void adjust_sPath(){
        int column =1;
        while (column<nVerts){
            if (vertexList[column].isInTree)
            {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column];

            int startToFringe = startToCurrent + currentToFringe;

            int sPathDist = sPath[column].distance;

            if (startToFringe<sPathDist){
                sPath[column].parentVert=currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }

    }
    public void displayPath(){
        for (int j=0;j<nVerts;j++){
            System.out.println(vertexList[j].label+"=");
            if (sPath[j].distance == INFINITY)
                System.out.println("inf");
            else
                System.out.println(sPath[j].distance);
            char parent = vertexList[sPath[j].parentVert].label;
            System.out.println("("+parent+")");


        }
        System.out.println("");
    }


}
