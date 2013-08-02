package fr.guillaume.paramelle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

import fr.guillaume.paramelle.client.resources.AppClientBundle;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyListApp implements EntryPoint {

	public void onModuleLoad() {
	    // set uncaught exception handler
	    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
	      public void onUncaughtException(Throwable throwable) {
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
	        DialogBox dialogBox = new DialogBox(true, false);
	        DOM.setStyleAttribute(dialogBox.getElement(), "backgroundColor", "#ABCDEF");
	        System.err.print(text);
	        text = text.replaceAll(" ", "&nbsp;");
	        dialogBox.setHTML("<pre>" + text + "</pre>");
	        dialogBox.center();
	      }
	    });

	    // use a deferred command so that the handler catches onModuleLoad2() exceptions
	    DeferredCommand.addCommand(new Command() {
	      public void execute() {
	        onModuleLoad2();
	      }
	    });
	  }

	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad2() {

		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());

		AppClientBundle.INSTANCE.css().ensureInjected() ;
		
		RootPanel.getBodyElement().addClassName(AppClientBundle.INSTANCE.css().body()) ;
		
		// build animation helper and attach it
		final AnimationHelper animationHelper = new AnimationHelper();
		RootPanel.get().add(animationHelper);

		// build some UI
		final LayoutPanel helloPanel = new LayoutPanel();
		Button helloBtn = new Button("Hello mgwt");
		helloPanel.add(helloBtn);

		final LayoutPanel byePanel = new LayoutPanel();
		Button byeBtn = new Button("Bye mgwt");
		byePanel.add(byeBtn);

		helloBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				animationHelper.goTo(byePanel, Animation.SLIDE) ;
			}
		}) ;
		
		byeBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				animationHelper.goTo(helloPanel, Animation.SLIDE_REVERSE);
				
			}
		}) ;
		
		ListView listView = new ListView() ;
		
		// animate
		animationHelper.goTo(listView, Animation.SLIDE);

	}
}
