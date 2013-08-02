package fr.guillaume.paramelle.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

import fr.guillaume.paramelle.client.resources.AppClientBundle;

public class ListView extends Composite {

	private FlowPanel panel ;
	private MTextBox inputField ;
	
	public ListView() {
		panel = new FlowPanel() ;
		inputField = new MTextBox() ;
		inputField.addStyleName(AppClientBundle.INSTANCE.css().inputField());
		
		panel.add(inputField);
		
		final PickupDragController dragController = new PickupDragController(RootPanel.get(), false) ;
		dragController.setBehaviorConstrainedToBoundaryPanel(true);
		MyFlowPanelDropController flowPanelDropController = new MyFlowPanelDropController(panel) ;
		dragController.registerDropController(flowPanelDropController);
		
		inputField.addBlurHandler(new BlurHandler() {
			@Override public void onBlur(BlurEvent event) {
				SimplePanel w = createItem(inputField.getText()) ;
				dragController.makeDraggable(w, w.getWidget());
				panel.insert(w, 0);
				
				inputField.setText("");
			}
		}) ;
		
		initWidget(panel);
	}
	
	private SimplePanel createItem(String text) {
		
		Label item = new Label(text) ;
		SimplePanel simplePanel = new SimplePanel(item) ;
		
		item.addStyleName(AppClientBundle.INSTANCE.css().listItem());
		
		return simplePanel ;
	}
	
}
