Kristine Trinh	
nlt895	
11190412

In this assignment, I have submitted the sourcec code in the "src" folder. Along with that are:

	-The final report after running 2 simulations
	-Runner.java runs every simulation 1000 times and then counts average result for both
	-Output file of each class that run the simulation
	-Most of the result output files can be read by wordpad (in "output files" folder)

Explanation about how program works.

First it creates new CUSTOMER_ARRIVE event and pushes it to EventQueue.

Then in a loop it starts to take events from EventQueue and handle them accordingly depending on type. Also it creates new Events.

That's how loop goes:
	if event type is CUSTOMER_ARRIVE:
		- it creates random number (1 - 6) of customers and pushes them to shortest CustomerQueue (or in simulation #2 to the only CustomerQueue)
		- counts statistic of queue length
		- schedules next CUSTOMER_ARRIVE event on random time (from 2 to 110 seconds)
		- schedules CUSTOMER_LEAVES_QUEUE event if window is free
		- writes log to file
	
	if event type is CUSTOMER_LEAVES_QUEUE:
		- takes customer from queue and puts him to the window that was freed (or is free at the first iteration of loop)
		- schedules event CUSTOMER_LEAVES_WINDOW on random time (from 55 to 185 seconds)
		- writes log to file
	
	if event type is CUSTOMER_LEAVES_WINDOW:
		- takes customer to list of served customers (is needed for counting statistics on customer waiting time)
		- marks window as free
		- schedules CUSTOMER_LEAVES_QUEUE event to the freed window
		- writes log to file
		
		
After 6 simulated hours passed loop stops.
Then program counts all statistic and writes it to log file and to console