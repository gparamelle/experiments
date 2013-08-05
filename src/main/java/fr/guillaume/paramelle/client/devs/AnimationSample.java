package fr.guillaume.paramelle.client.devs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

public class AnimationSample {

	public void sampleCode() {
		
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
		
		// animate
		animationHelper.goTo(helloPanel, Animation.SLIDE);

	}
	
}
