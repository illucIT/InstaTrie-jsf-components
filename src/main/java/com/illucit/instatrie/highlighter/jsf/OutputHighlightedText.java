package com.illucit.instatrie.highlighter.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import com.illucit.instatrie.highlight.HighlightedString;


/**
 * Output component for {@link HighlightedString} value.
 *
 * @author Christian Simon
 * @author Christian Ott
 *
 */
@SuppressWarnings("javadoc")
@FacesComponent(value = "com.illucit.faces.component.OutputHighlightedText")
public class OutputHighlightedText extends UIComponentBase {

	public static final String COMPONENT_TYPE = "com.illucit.faces.component.OutputHighlightedText";
	public static final String COMPONENT_FAMILY = "com.illucit.faces.component";
	private static final String DEFAULT_RENDERER = "com.illucit.faces.component.OutputHighlightedTextRenderer";


	protected enum PropertyKeys {
		value, containsHtml, search
	}

	public OutputHighlightedText() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getValue() {
		return (String) getStateHelper().eval(PropertyKeys.value, null);
	}

	public void setValue(String _value) {
		getStateHelper().put(PropertyKeys.value, _value);
	}

	public String getSearch() {
		return (String) getStateHelper().eval(PropertyKeys.search, null);
	}

	public void setSearch(String _search) {
		getStateHelper().put(PropertyKeys.search, _search);
	}

	public boolean isContainsHtml() {
		return (Boolean) getStateHelper().eval(PropertyKeys.containsHtml, false);
	}

	public void setContainsHtml(boolean _containsHtml) {
		getStateHelper().put(PropertyKeys.containsHtml, _containsHtml);
	}

}