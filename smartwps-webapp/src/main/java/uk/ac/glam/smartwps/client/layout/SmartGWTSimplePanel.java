package uk.ac.glam.smartwps.client.layout;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * Wrapper class for SmartGWT layout so that it implements HasOneWidget.
 * @author Jon Britton
 */
public class SmartGWTSimplePanel extends HLayout implements HasOneWidget {

	private Widget child;
    
	@Override
	public void setWidget(IsWidget w) {
		child = (Widget) w;
		addMember(child);
	}

	@Override
	public Widget getWidget() {
		return child;
	}

	@Override
	public void setWidget(Widget w) {
		removeMembers(getMembers());
	}

}
