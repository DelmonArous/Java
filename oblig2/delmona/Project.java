import java.io.*;
import java.util.*;

public class Project {
	private int maxnr; // number of tasks
	private Task[] tasks; // tasks of the project
	private Event[] events; // events of the project (converted from a activity-node graph)
	private LinkedList<Integer> topologicalorder;
	
	/*
	 * A task is a node in the activity-node graph, defining the whole project.
	 */
	private class Task {
		int id, time, staff; // unique id, time needed and manpower needed for completion
		String name; // name of the task
		int earlieststart, lateststart, slack; // earliest, latest and slack start time (due to task-dependencies)
		int earliestcompletion, latestcompletion;
		LinkedList<TaskEdge> outEdges; // all the outgoing edges of the task
		int cntPredecessors;
		boolean active;
		
		Task(int id) {
			this.id = id;
			outEdges = new LinkedList<TaskEdge>();
			cntPredecessors = 0;
		}
		
		/*
		 * Adds an edge pointing from this task, u, to the task with given id, v.
		 */
		void addEdge(int id) {

			if (id == 0)
				return;
			
			cntPredecessors++;
			outEdges.add(new TaskEdge(id)); 
		}
		
	}
	
	/*
	 * TaskEdge is a directed edge (u,v) in the activity-node graph 
	 * that means task u must be completed before task v may begin.
	 */
	private class TaskEdge {
		Task task;
		
		TaskEdge(int id) {
			task = getTask(id);
		}
	}
	
	
	/*
	 * An event is a node in the event-node graph (converted from a given activity-node graph).
	 * Each event corresponds to the completion of a task and all its dependent tasks, i.e. each 
	 * task are now edges between two events (a dummy and a real event) and are weighted by the task completion time. 
	 * Events reachable from a node v in the event-node graph may not commence until after the event
	 * v is completed. Dummy event nodes are inserted in order to avoid false dependencies (or false lack of dependencies). 
	 * The id of a real and its dummy event, are the same.
	 */
	private class Event {
		int id;
        boolean dummy;
        LinkedList<EventEdge> inEdges;
        LinkedList<EventEdge> outEdges;
        int earliestcompletion, latestcompletion;
        
        Event(int id, boolean dummy) {
        	this.id = id;
        	this.dummy = dummy;
            inEdges = new LinkedList<EventEdge>();
            outEdges = new LinkedList<EventEdge>();
            earliestcompletion = 0;
            latestcompletion = Integer.MAX_VALUE;
        }
        
        /*
         * Adds an outgoing edge from this event to a given event, and gives it weight
         */
        void addOutEdge(Event event, int time) {
        	event.addInEdge(this, time); // automatically adds this as ingoing edge for the given event
        	outEdges.add(new EventEdge(event, time));
        }
        
        void addInEdge(Event event, int time) {
        	inEdges.add(new EventEdge(event, time));
        }
	}

	/*
	 * EventEdge is an directed edge between two events in the event-node graph.
	 * These edges are weighted by time cost of tasks. 
	 * Edges pointing to dummy events will have zero time cost.
	 */
	private class EventEdge {
		Event event;
		int time;
		
		EventEdge(Event event, int time) {
			this.event = event;
			this.time = time;
		}
	}
	
	/*
	 * Constructor reads in input-file and constructs the activity-node graph.
	 */
	public Project(String filename) {
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		}
	
		maxnr = Integer.parseInt(scan.nextLine()); // read in number of tasks
		scan.nextLine();
		
		// Construct all tasks
		tasks = new Task[maxnr];
		for (int id = 0; id < maxnr; id++)
			tasks[id] = new Task(id+1);
		
		// Read in info about each task
		for (Task t: tasks) {
			String line = scan.nextLine();
            String[] info = line.split("\\s+");
            String name = info[1]; // read in task name
            int time = Integer.parseInt(info[2]); // read in completion time of task 
            int staff = Integer.parseInt(info[3]); // read in manpower needed of task
            t.name = name;
            t.time = time;
            t.staff = staff;
            
            // Read in dependencies between tasks and construct complementary edges 
            for (int i = 4; i < info.length; i++)
            	if (Integer.parseInt(info[i]) != 0)
            		getTask(Integer.parseInt(info[i])).addEdge(t.id);
            	//t.addEdge(Integer.parseInt(info[i]));
		}
		
        // Allocate an array to store a topological order of the project
		topologicalorder = new LinkedList<Integer>();
	}
	
	/*
	 * Return a task given by its id.
	 */
	public Task getTask(int id) {
		return tasks[id-1];
	}
	
	/*
	 * Return a dummy event corresponding to a task of a given id.
	 */
    public Event getInEvent(int id) {
        return events[2*id-2];
    }

    /*
     * Return a real event corresponding to a task of a given id.
     */
    public Event getOutEvent(int id) {
        return events[2*id-1];
    }
	
    /*
     * Construct a event-node graph from a given activity-node graph. There are
     * two node events for each task in the activity-node graph, in-events (dummy)
     * and out-events (real events). The edge between an in-event and out-event
     * is weighted by the completion time of the corresponding task. 
     * The id of a real and its dummy event, are the same.
     * The event edges pointing to in-events will have zero time cost.
     * We also construct a "finish"-event that corresponds the completion of the entire project.
     */
    public void constructEventNodeGraph() {
        events = new Event[2*maxnr+2];
        
        for (int id = 1; id <= maxnr; id++) {
            events[2*id-2] = new Event(id, true); // in-event (dummy)
            events[2*id-1] = new Event(id, false); // out-event
            getInEvent(id).addOutEdge(getOutEvent(id), getTask(id).time);
        }
        
        for (Task t: tasks)
            for (TaskEdge edge: t.outEdges)
                getOutEvent(t.id).addOutEdge(getInEvent(edge.task.id), 0);
        		// getOutEvent(edge.task.id).addOutEdge(getInEvent(t.id), 0);


        // Adds the "finish" node event representing the completion of the project
        events[2*maxnr] = new Event(0, true);
        events[2*maxnr+1] = new Event(0, false);
        getInEvent(maxnr+1).addOutEdge(getOutEvent(maxnr+1), 0);
        for (int i = 0; i < 2*maxnr; i++)
            events[i].addOutEdge(getInEvent(maxnr+1), 0);
        
        // Adds the last event last in the topological order 
        topologicalorder.add(maxnr + 1);
    }
	
    /*
     * Depth-first search of the activity-node graph. If a backward is detected,
     * the graph is cyclic and the project is therefore not realizable.
     * We also construct a topological order of the activity-node graph.
     */
	public boolean dfs() {
		LinkedList<Task> parent = new LinkedList<Task>();
		
		for (Task t: tasks) {
			if (!parent.contains(t)) {
				parent.add(t);
				parent = dfsVisit(parent, t);
				if (parent == null) { // task t is part of a cycle
					System.out.println(" --> " + t.id);
					return false;
				}
			}
		}
		
		return true;
	}
	
	/*
	 * Recursive depth-first search starting at a given task t.
	 * If a cycle is detected, the recursion is broken and null is returned. 
	 */
	public LinkedList<Task> dfsVisit(LinkedList<Task> parent, Task t) {
		t.active = true; // mark this node as active
		Task edgetask;
		
		// Loop over the edges of task t
		for (TaskEdge edge: t.outEdges) {
			edgetask = edge.task;
			if (edgetask == null) {
				t.active = false;
				topologicalorder.addFirst(t.id);
				return parent;
			}
			if (edgetask.active) { // cycle is detected
				System.out.print("Circular dependency in the project, cycle: " + edgetask.id);
				return null;
			}
			if (!parent.contains(edgetask)) { // recursively visit new task nodes
				parent.add(edgetask);
				parent = dfsVisit(parent, edgetask);
				if (parent == null) { // task edgetask is part of a cycle
					System.out.print(" --> " + edgetask.id);
					return null;
				}
			}
		}
		
		t.active = false;
		topologicalorder.addFirst(t.id);
		
		return parent;
	}

	/*
	 * Computing the earliest completion time for all the event nodes in the event-node graph.
	 * This is done by traversing the event-edges in topological order.
	 * If EC_i is the earliest completion time for node i, then
	 * 	EC_1 = 0
	 * 	EC_w = max( EC_v + c_(v,w) ) for all event-edges (v,w)
	 */
	public void findEarliestStartingTimes() {
		
		for (Integer id: topologicalorder) {
			Event[] inoutevents = {getInEvent(id), getOutEvent(id)};
			for (Event e: inoutevents)
				for (EventEdge edge: e.outEdges) {
					int oldtime = edge.event.earliestcompletion;
					int newtime = e.earliestcompletion + edge.time;
					edge.event.earliestcompletion = max(oldtime, newtime);
				}
		}
		
		for (Task t: tasks) {
			t.earliestcompletion = getOutEvent(t.id).earliestcompletion;
			t.earlieststart = t.earliestcompletion - t.time;
		}
		
	}
	
	/*
	 * Computing the latest completion time for all the event nodes in the event-node graph.
	 * This is done by traversing the event-edges in reverse topological order.
	 * If LC_i is the latest completion time for node i, then
	 * 	LC_n = EC_n
	 * 	LC_v = min( LC_w - c_(v,w) ) for all event-edges (v,w)
	 */
	public void findLatestStartingTimes() {
		// Last event, latest and earliest times are equal
		Event lastevent = getOutEvent(topologicalorder.get(maxnr));
		lastevent.latestcompletion = lastevent.earliestcompletion;
		
		ListIterator<Integer> iterator = topologicalorder.listIterator(topologicalorder.size()); // reverse iterator
		
		while (iterator.hasPrevious()) {
			Integer id = iterator.previous();
			Event[] inoutevents = {getOutEvent(id), getInEvent(id)};
			for (Event e: inoutevents)
				for (EventEdge edge: e.inEdges) {
					int oldtime = edge.event.latestcompletion;
					int newtime = e.latestcompletion - edge.time;
					edge.event.latestcompletion = min(oldtime, newtime);
				}
		}
		
		for (Task t: tasks) {
			t.latestcompletion = getOutEvent(t.id).latestcompletion;
			t.lateststart = t.latestcompletion - t.time;
		}
		
	}
	
	/*
	 * Computing the slacks of all the tasks.
	 */
	public void findSlackTimes() {
		for (Task t: tasks)
			t.slack = t.lateststart - t.earlieststart;
	}
	
    public int max(int a, int b) {
        return (a > b ? a : b);
    }

    public int min(int a, int b) {
        return (a < b ? a : b);
    }
    
    public void printResults() {
    	System.out.println("---- Information on all tasks ----");
    	
    	for (Task t: tasks) {
    		System.out.println("Task " + t.id + ", " + t.name);
    		System.out.println("\tTime: " + t.time);
    		System.out.println("\tStaff: " + t.staff);
    		if (t.slack == 0)
    			System.out.println("\tThis task is critical");
    		else
    			System.out.println("\tSlack: " + t.slack);
    		System.out.println("\tLatest starting time: " + t.lateststart);
    		
    		if(t.outEdges.isEmpty())
    			System.out.println("There are no dependency on this task");
    		else {
				System.out.println("Dependency on this task: ");
    			for (TaskEdge edge: t.outEdges) {
    				System.out.println("\tTask " + edge.task.id + ", " + edge.task.name);
    			}
    		}
    		
    		System.out.println(" ");
    	}
    	
    }
    
    public void printProjectWorkLog() {
    	int time = -1;
    	int tasksfinished = 0;
    	int currentstaff = 0;
    	boolean flag = false;
    	
    	System.out.println("---- Project work log ----");
    	while (tasksfinished < maxnr) {
    		time++;
    		flag = false;
    		
    		for (Task t: tasks) {
    			if (t.earlieststart == time) {
    				if (!flag) {
    					System.out.println("Time: " + time);
    					flag = true;
    				}
    				System.out.println("\tStarting: " + t.id);
    				currentstaff += t.staff;
                }
    			if (t.earliestcompletion == time) {
    				if (!flag) {
    					System.out.println("Time: " + time);
    					flag = true;
    				}
    				System.out.println("\tFinished: " + t.id);
    				tasksfinished++;
    				currentstaff -= t.staff;
    			}
    		}
    		if (flag)
    			System.out.println("\tCurrent Staff: " + currentstaff);
    	}
    	System.out.println("\n**** Shortest possible project execution is " + time + " ****");
    }
    
}