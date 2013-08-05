package fr.guillaume.paramelle.client.widget;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.FlowPanelDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.allen_sauer.gwt.dnd.client.util.LocationWidgetComparator;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.guillaume.paramelle.client.resources.AppClientBundle;

public class MyFlowPanelDropController extends FlowPanelDropController {

	  /**
	   * Label for IE quirks mode workaround.
	   */
	private static final Label DUMMY_LABEL_IE_QUIRKS_MODE_OFFSET_HEIGHT = new Label("x");

	private ActionOnDrop actionOnDrop ;
	
	public MyFlowPanelDropController(FlowPanel dropTarget, ActionOnDrop actionOnDrop) {
		super(dropTarget);
		this.actionOnDrop = actionOnDrop ;
	}
	
	
	 @Override
	  protected LocationWidgetComparator getLocationWidgetComparator() {
	    return LocationWidgetComparator.LEFT_HALF_COMPARATOR ;
	  }
	
	@Override
	  protected Widget newPositioner(DragContext context) {
	    // Use two widgets so that setPixelSize() consistently affects dimensions
	    // excluding positioner border in quirks and strict modes
	    SimplePanel outer = new SimplePanel();
	    outer.addStyleName(AppClientBundle.INSTANCE.css().itemPositioner());

	    // place off screen for border calculation
	    RootPanel.get().add(outer, -500, -500);

	    // Ensure IE quirks mode returns valid outer.offsetHeight, and thus valid
	    // DOMUtil.getVerticalBorders(outer)
	    outer.setWidget(DUMMY_LABEL_IE_QUIRKS_MODE_OFFSET_HEIGHT);

	    int width = 0;
	    int height = 0;
	    for (Widget widget : context.selectedWidgets) {
	      width = Math.max(width, widget.getOffsetWidth());
	      height += widget.getOffsetHeight();
	    }

	    SimplePanel inner = new SimplePanel();
	    inner.setPixelSize(width - DOMUtil.getHorizontalBorders(outer), height
	        - DOMUtil.getVerticalBorders(outer));

	    outer.setWidget(inner);

	    return outer;
	}
	
	
	
	@Override
	public void onDrop(DragContext context) {
		super.onDrop(context);
		actionOnDrop.onDrop();
	}

	public interface ActionOnDrop {
		public void onDrop() ;
	}
}
