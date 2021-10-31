Godbless Gregory Chille

How to run:
The command line arguments and their order are really important in the output file. The format for running on the terminal is :
java ProgramName map.txt [-show] [-directions startIntersection
endIntersection] [-meridianmap] 
An example of this is:
java Graph ur.txt -show i1 i20

The Edge class has the following attributes: road id, the two nodes of intersection and weight(Calculated using Pythagorus theorem). It extends Comparable interface because each Edge has a weight attached to it.
The Node class has the following attributes: unique id, latitude, longitude, distance from source, previous Node and check(to see if the Node has been covered or not). The get path method in the algorithm helps to show the path drawn when Dijkstra's algorithm is implemented.

The DijkstraAlgo class is responsible for computing the shortest path between the two nodes s and t (node ids) entered by the user. 
**IMPORTANT**
To check the time complexity of dijkstraAlgo I make use of 'start' and 'end' variables which calculates the time the algorithm takes to run.
The final time taken is presented on the terminal.
Through careful analysis we can find that the big O of dijkstrasAlgo is O(ElogV)

The KruksalsAlgo class is responsible for computing the minimum weight spanning tree for the meridian
**IMPORTANT**
To check the time complexity of KrukshalsAlgo we make use of 'start' and 'end' variables which calculates the time the algorithm takes to run.
The final time taken is presented on the terminal.
Through careful analysis we can find that the big O of KruksalsAlgo is O(ElogE)

The GraphGUI class is responsible for the graphics part -plotting the map and scaling it. If the user wants to see the map he must make use of "-show" on the command line. drawPath method is used to draw the lines in path found between the two intersection in GREEN and drawline method is used to draw the minimum weight spanning tree in RED.


