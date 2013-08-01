package fr.guillaume.paramelle.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ListView extends Composite {

	private FlowPanel panel ;
	private TextBox inputField ;
	
	public ListView() {
		panel = new FlowPanel() ;
		inputField = new TextBox() ;
		
		panel.add(inputField);
		
		final PickupDragController dragController = new PickupDragController(RootPanel.get(), false) ;
		MyFlowPanelDropController flowPanelDropController = new MyFlowPanelDropController(panel) ;
		dragController.registerDropController(flowPanelDropController);
		
		inputField.addBlurHandler(new BlurHandler() {
			@Override public void onBlur(BlurEvent event) {
				Label item = new Label(inputField.getText()) ;
				inputField.setText("");
				dragController.makeDraggable(item);
				panel.insert(item, 0);
			}
		}) ;
		
		initWidget(panel);
	}
	
}
