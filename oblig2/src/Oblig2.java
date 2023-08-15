public class Oblig2 {
	public static void main(String[] args) {
		// Read in command-line arguments
		if (args.length != 2) {
			System.out.println("InputError!");
			System.exit(0);
		}
		
		String inputfile = args[0];
		int manpower = Integer.parseInt(args[1]);
		
		Project project = new Project(inputfile);
		
    	// Run critical path analysis
		if (!project.dfs()) {
    		System.out.println("Terminating the project...");
    		System.exit(0);
    	}
    	
    	project.constructEventNodeGraph();
    	
    	project.findEarliestStartingTimes();
    	project.findLatestStartingTimes();
    	project.findSlackTimes();
		
		// Print project information
		project.printResults();
		project.printProjectWorkLog();
	}
}