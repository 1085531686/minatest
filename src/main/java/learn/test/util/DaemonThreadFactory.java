package learn.test.util;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {  
	
	private ThreadGroup threadGroup = new ThreadGroup("handlerworkGroup"); 
	private  int cnt =0;
	@Override
    public Thread newThread(Runnable r) {  
	   int cnt =getCntAndSet();
       Thread t = new Thread(threadGroup,r,"handlerThread-"+cnt);  
       t.setDaemon(true);  
       return t;  
    }
	
	public synchronized  int getCntAndSet() 
	{
		int localcnt =cnt;
		cnt++;
		return localcnt;
	}
	
	
	
}  
