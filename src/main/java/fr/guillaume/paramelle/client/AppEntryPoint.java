package fr.guillaume.paramelle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;

import fr.guillaume.paramelle.client.dao.ClientDao;
import fr.guillaume.paramelle.client.dao.MyClientStorage;
import fr.guillaume.paramelle.client.resources.AppClientBundle;
import fr.guillaume.paramelle.client.widget.ListView;
import fr.guillaume.paramelle.client.widget.ListView.Listener;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppEntryPoint implements EntryPoint {

	public void onModuleLoad() {
	    
	    uncaughtExceptionHandler();
	    
	    Scheduler.get().scheduleDeferred(
	    	new ScheduledCommand() {
				@Override public void execute() {
					main() ;
				}
	    	}) ;
	  }



	
	/**
	 * This is the entry point method.
	 */
	public void main() {

		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());

		AppClientBundle.INSTANCE.css().ensureInjected() ;
		
		RootPanel.getBodyElement().addClassName(AppClientBundle.INSTANCE.css().body()) ;
		
			
		Listener presenter = new ListPresenter(new ClientDao(new MyClientStorage())) ;
		ListView listView = new ListView(presenter) ;
		
		RootPanel.get().add(listView);

	}
	
	/**
	 * 
	 */
	private void uncaughtExceptionHandler() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable throwable) {
				String text = AppEntryPoint.formatStackTrace(throwable);
				HTML html = new HTML("<pre>"+text+"</pre>");
				RootPanel.get().remove(0) ;
				RootPanel.get().add(html);
			}
		}) ;
	}

	/**
	 * 
	 * @param throwable
	 * @return
	 */
	private static String formatStackTrace(Throwable throwable) {
		String text = "Uncaught exception: ";
        while (throwable != null) {
          StackTraceElement[] stackTraceElements = throwable.getStackTrace();
          text += throwable.toString() + "\n";
          for (int i = 0; i < stackTraceElements.length; i++) {
            text += "    at " + stackTraceElements[i] + "\n";
          }
          throwable = throwable.getCause();
          if (throwable != null) {
            text += "Caused by: ";
          }
        }
		return text;
	}
}
