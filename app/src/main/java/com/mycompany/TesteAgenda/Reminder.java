package com.mycompany.TesteAgenda;


	/**
	 * Simple demo that uses java.util.Timer to schedule a task 
	 * to execute once 5 seconds have passed.
	 */

import android.util.*;
import java.util.*;

public class Reminder
 {
		Timer timer;

		public Reminder(int seconds) {
			timer = new Timer();
			timer.schedule(new RemindTask(), seconds*1000);
		}

		class RemindTask extends TimerTask {
			public void run() {
				System.out.println("Time's up!");
				Log.v("msg ", "Time is up");
				//timer.cancel(); //Terminate the timer thread
			}
		}

		
	
}
