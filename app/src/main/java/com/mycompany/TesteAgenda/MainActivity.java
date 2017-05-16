package com.mycompany.TesteAgenda;

import android.app.*;
import android.icu.util.*;
import android.os.*;
import java.util.*;

import android.icu.util.Calendar;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
	
		new AnnoyingBeep();
		System.out.println("Task scheduled.");
	
		
}

	/*public void executarTarefas(int seconds) {
		Timer timer = new Timer();
		//logamsg logmsg = new logamsg();
		timer.schedule(new logamsg(), Calendar.getInstance().getTime(), seconds * 1000);   
	}*/


}
