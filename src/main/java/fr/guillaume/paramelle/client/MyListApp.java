package fr.guillaume.paramelle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyListApp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());

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
