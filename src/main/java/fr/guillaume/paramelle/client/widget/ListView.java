package fr.guillaume.paramelle.client.widget;

import java.util.ArrayList;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

import fr.guillaume.paramelle.client.resources.AppClientBundle;
import fr.guillaume.paramelle.client.widget.MyFlowPanelDropController.ActionOnDrop;

public class ListView extends Composite {

	private final Listener listener ;
	private final FlowPanel panel ;
	private final MTextBox inputField ;
	private final Timer delayedOnChange;
	
	
	public ListView(Listener listener) {
		this.listener = listener ;
		
		panel = new FlowPanel() ;
		inputField = new MTextBox() ;
		inputField.addStyleName(AppClientBundle.INSTANCE.css().inputField());
		
		this.delayedOnChange = new Timer() {
			@Override public void run() {
				ListView.this.listener.onChange(getModelList());
			}
		};
		
		final PickupDragController dragController = new PickupDragController(RootPanel.get(), false) ;
		
		MyFlowPanelDropController flowPanelDropController = new MyFlowPanelDropController(panel, new ActionOnDrop() {
			@Override public void onDrop() {
				viewChanged();
			}
		}) ;
		
		dragController.registerDropController(flowPanelDropController) ;
		
		inputField.addBlurHandler(new BlurHandler() {
			@Override public void onBlur(BlurEvent event) {
				
				ItemWidget itemWidget = new ItemWidget(inputField.getText().trim()) ;
				dragController.makeDraggable(itemWidget, itemWidget.getDragHandler());
				panel.insert(itemWidget, 0);
				viewChanged();
				
				inputField.setText("");
			}
		}) ;
		
		String[] lines = listener.onPrepare() ;
		for (String line : lines) {
			ItemWidget itemWidget = new ItemWidget(line) ;
			dragController.makeDraggable(itemWidget, itemWidget.getDragHandler());
			panel.add(itemWidget);
		}
		
		panel.add(inputField);
		
		initWidget(panel);
	}
	
	
	/**
	 * @return model list representation
	 */
	private String[] getModelList() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0 ; i < panel.getWidgetCount() ; i++) {
			Widget widget = panel.getWidget(i) ;
			if ( widget instanceof HasText ) {
				HasText hasText = (HasText) widget ;
				if ( hasText.getText().trim().length() > 0 )
					list.add(hasText.getText()) ;
			}
		}
		return list.toArray(new String[0]) ;
	}
	
	/**
	 * 
	 */
	private void viewChanged() {
		delayedOnChange.cancel();
		delayedOnChange.schedule(1000);
	}


	/** item in list */
	private static class ItemWidget extends Composite implements HasText {

		private final Label label ;
		
		public ItemWidget(String text) {
			label = new Label(text) ;
			label.addStyleName(AppClientBundle.INSTANCE.css().listItem());
			SimplePanel simplePanel = new SimplePanel(label);
			initWidget(simplePanel);
		}
		
		@Override
		public String getText() {
			return label.getText() ;
		}

		@Override
		public void setText(String text) {
			label.setText(text);
		}
		
		public Widget getDragHandler() {
			return label ;
		}
	}
	
	
	public interface Listener {
		
		public void onChange(String[] itemList) ;
		
		public String[] onPrepare() ;
	}
}
