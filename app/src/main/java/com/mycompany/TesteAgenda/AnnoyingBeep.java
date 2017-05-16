package com.mycompany.TesteAgenda;


	//repetida 1 vez por segundo
import android.util.*;
import java.util.*;

public class AnnoyingBeep
 {
		//Toolkit toolkit;
		Timer timer;

		public AnnoyingBeep() {
		//	toolkit = Toolkit.getDefaultToolkit();
			timer = new Timer();
			timer.schedule(new RemindTask(),
						   0,        //initial delay
						   3*1000);  //subsequent rate
		}

		class RemindTask extends TimerTask {
			int numWarningBeeps = 3;
			public void run() {
				if (numWarningBeeps > 0) {
					//toolkit.beep();
					Log.v("mensagem","beep " + Integer.toString(numWarningBeeps));
					//System.out.println("Beep!");
					numWarningBeeps--;
				} else {
					//toolkit.beep(); 
					//System.out.println("Time's up!");
					//timer.cancel(); // Not necessary because
					// we call System.exit
					Log.v("mensagem"," Last beep " + Integer.toString(numWarningBeeps));
					System.exit(0);   // Stops the AWT thread 
					// (and everything else)
				}
				
			}
		}
}
