import java.util.*;

public class MST
{
   public static void main(String[] args)
   {
	   Vertex a = new Vertex("A", 0, false);
	   Vertex b = new Vertex("B", 1, false);
	   Vertex c = new Vertex("C", 2, false);
	   Vertex d = new Vertex("D", 3, false);
	   Vertex e = new Vertex("E", 4, false);
	   Vertex f = new Vertex("F", 5, false);
	   Vertex g = new Vertex("G", 6, false);

	   Vertex[] vertices = {a, b, c, d, e, f, g};

	   Edge[][] edges = {
		   {new Edge(0, 1, 8), new Edge(0, 2, 10), new Edge(0, 3, 13)},
		   {new Edge(1, 0, 8), new Edge(1, 3, 16)},
		   {new Edge(2, 0, 10), new Edge(2, 3, 14), new Edge(2, 5, 12)},
		   {new Edge(3, 0, 13), new Edge(3, 1, 16), new Edge(3, 2, 14), new Edge(3, 4, 15), new Edge(3, 6, 11)},
		   {new Edge(4, 3, 15), new Edge(4, 5, 5), new Edge(4, 6, 6)},
		   {new Edge(5, 2, 12), new Edge(5, 4, 5), new Edge(5, 6, 7)},
		   {new Edge(6, 3, 11), new Edge(6, 4, 6), new Edge(6, 5, 7)}
	   };

	   Graph graph = new Graph(vertices,edges);

	   graph.findMST(e);
   }
}

class Vertex
{
	String name;
	int index;
	boolean visited;

	Vertex (String name, int index, boolean visited)
	{
		this.name = name;
		this.index = index;
		this.visited = visited;
	}
}

class Edge
{
	int startIndex;
	int endIndex;
	int value;

	Edge (int startIndex, int endIndex, int value)
	{
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.value = value;
	}
}

class Graph
{
	Vertex[] vertices;
	Edge[][] edges;

	Graph (	Vertex[] vertices, Edge[][] edges)
	{
		this.vertices = vertices;
		this.edges = edges;
	}

	public void findMST(Vertex x)
	{
	  if(edges.length != vertices.length)
      {
		  System.out.println("Some of the houses are not connected! \nProvide all the edges values to find the minimum spanning tree!");
	  }
      else
      {

		Vertex current = x;

		current.visited = true;

		int cost = 0;

		Edge[] path = new Edge[vertices.length-1];

		Vertex[] reached = new Vertex[vertices.length];

		int count = 0;
		for(int k = 0; k < vertices.length-1; k++)
		{
			for(int i = 0; i < edges[current.index].length-1; i++)
			{
				if(edges[k][i].value != edges[0][0].value)
				{
					count++;
				}
			}
		}

	   if(count == 0)
	   {
		    System.out.println("All the edges have the same value! Every possible connecting path will have the same value.");
	   }
	   else
	   {

		for(int k = 0; k < vertices.length-1; k++)
		{
			reached[k] = current;
			current.visited = true;

			Edge minWeight = edges[current.index][0];

			for(int i = 0; i < edges[current.index].length; i++)
			{
				if(edges[current.index][i].value < minWeight.value)
				{
					minWeight = edges[current.index][i];
				}
			}

			int p = 0;
			int counter = 0;


			while(vertices[minWeight.endIndex].visited == true || p <= k )
			{
				for(int i = 0; i < edges[current.index].length; i++)
				{
					if(counter == 0 && edges[current.index][i].value != minWeight.value && vertices[edges[current.index][i].endIndex].visited == false)
					{
						minWeight = edges[current.index][i];
						counter++;
					}
					else if(counter > 1 && edges[current.index][i].value < minWeight.value)
					{
						minWeight = edges[current.index][i];
					}
				}
				for(int i = 0; i < edges[reached[p].index].length; i++)
				{
					if(edges[reached[p].index][i].value < minWeight.value && vertices[edges[reached[p].index][i].endIndex].visited == false)
					{
						minWeight = edges[reached[p].index][i];
					}
				}
				p++;
			}

			cost += minWeight.value;
			path[k] = minWeight;
			current = vertices[minWeight.endIndex];
	    }

	    System.out.println("The lowest total cost is " + cost + " thousand.");
	    System.out.println("The path with the lowest total cost is:");
	    for(int h = 0; h < vertices.length-1; h++)
		{
			System.out.println("from " + vertices[path[h].startIndex].name + " to " + vertices[path[h].endIndex].name);
		}
	   }
	  }
	}
}