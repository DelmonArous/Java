Mandatory assignment 2

- How to compile and run the program:
	javac *.java
	java Oblig2 buildhouse1.txt 999
	java Oblig2 buildhouse2.txt 999
	java Oblig2 buildrail.txt 999

- In this assignment I have assumed unlimited manpower, and have not implementet the optional part.

- There are no peculiarities about the implementation:
	We construct an activity-node graph from the given input-files, and convert this into an event-node graph
	to find earliest, latest and slack times of each task in the activity-node graph (outlined in section 9.3.4 of Weiss). 
	There are constructed more dummy events than necessary, and therefore affect the complexity.

- Complexity of the implementation:
	DFS (checking if project is realizable)	- complexity O(|E|+|V|).
	Constructing event-node graph 		- complexity O(|E|+|V|), since there are constant number of operation per node and per edge in the acitvity-node graph.
	Iterating through the event-node graph 	- complexity O(|E|+|V|), since each node and edge are visited once.
	Critical path analysis			- complexity O(|E|+|V|) for computing earliest and latest starting times, and complexity O(|V|) for computing slack times of each task
	The total complexity is thus O(|E|+|V|).

I have cooperated with Saurav Sharma (sauravsh) in this assignment.