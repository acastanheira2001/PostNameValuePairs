package com.mycompany.TesteAgenda;

import android.util.*;

public class logamsg
{
	private int contador=0;
	
	public void logainfo(){
	
		contador = contador + 1;
		Log.v("mensagem " , Integer.toString(contador));
		}
}
