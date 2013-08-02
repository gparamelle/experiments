package fr.guillaume.paramelle.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

import fr.guillaume.paramelle.client.MyFlowPanelDropController.ActionOnDrop;
import fr.guillaume.paramelle.client.resources.AppClientBundle;

public class ListView extends Composite {

	private FlowPanel panel ;
	private MTextBox inputField ;
	
	
	public ListView() {
		panel = new FlowPanel() ;
		inputField = new MTextBox() ;
		inputField.addStyleName(AppClientBundle.INSTANCE.css().inputField());
		
		final PickupDragController dragController = new PickupDragController(RootPanel.get(), false) ;
		dragController.setBehaviorConstrainedToBoundaryPanel(true);
		MyFlowPanelDropController flowPanelDropController = new MyFlowPanelDropController(panel, new ActionOnDrop() {
			@Override
			public void onDrop() {
				new Timer() {
					
					@Override
					public void run() {
						save() ;
					}
				}.schedule(1000);
			}
		}) ;
		
		dragController.registerDropController(flowPanelDropController) ;
		
		inputField.addBlurHandler(new BlurHandler() {
			@Override public void onBlur(BlurEvent event) {
				SimplePanel w = createItem(inputField.getText()) ;
				dragController.makeDraggable(w, w.getWidget());
				panel.insert(w, 0);
				save();
				inputField.setText("");
			}
		}) ;
		
		String[] lines = restore() ;
		for (String line : lines) {
			SimplePanel w = createItem(line) ;
			dragController.makeDraggable(w, w.getWidget());
			panel.add(w);
		}
		
		panel.add(inputField);
		
		initWidget(panel);
	}
	
	private SimplePanel createItem(String text) {
		
		Label item = new Label(text) ;
		SimplePanel simplePanel = new SimplePanel(item) ;
		
		item.addStyleName(AppClientBundle.INSTANCE.css().listItem());
		
		return simplePanel ;
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder() ;
		int widgetCount = panel.getWidgetCount() ;
		for ( int i = 0 ; i < widgetCount ; i++ ) {
			if ( panel.getWidget(i) instanceof SimplePanel ) {
				SimplePanel sp = (SimplePanel) panel.getWidget(i) ;
				Label item = (Label) sp.getWidget() ;
				sb.append( item.getText()+"/" ) ;
			}
		}
		return sb.toString() ;
	}
	
	public void save() {
		try {
			Storage storage = Storage.getLocalStorageIfSupported() ;
			storage.setItem("plaintext", this.getText());
		} catch (Exception e) {
			// quiet : pour plus tard
		}
	}
	
	public String[] restore() {
		Storage storage = Storage.getLocalStorageIfSupported() ;
		if ( storage == null ) return new String[0] ;
		String plaintext = storage.getItem("plaintext") ;
		if ( plaintext == null ) return new String[0] ;
		return plaintext.split("/") ;
	}
}
