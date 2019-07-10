package com.illucit.instatrie.highlighter.jsf;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import com.illucit.instatrie.highlight.HighlightedString;
import com.illucit.instatrie.splitter.StringWordSplitter;


/**
 * Renderer for {@link OutputHighlightedText}.
 *
 * @author Christian Simon
 * @author Christian Ott
 *
 */
@FacesRenderer(rendererType = "com.illucit.faces.component.OutputHighlightedTextRenderer",
		componentFamily = "com.illucit.faces.component")
public class OutputHighlightedTextRenderer extends Renderer {

	private static final String HIGHLIGHT_TAG = "span";
	private static final String HIGHLIGHT_CSS_CLASS = "highlight";

	private static final StringWordSplitter<String> highlighter = StringWordSplitter.IdentityStringWordSplitter.instance();

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		OutputHighlightedText highlightedText = (OutputHighlightedText) component;

		String stringValue = highlightedText.getValue();
		String query = highlightedText.getSearch();
		boolean html = highlightedText.isContainsHtml();

		ResponseWriter writer = context.getResponseWriter();

		renderHighlighted(stringValue, query, html, writer);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// Do nothing
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	private void renderHighlighted(String stringValue, String query,
								  boolean html, ResponseWriter writer) throws IOException {

		if (isTrimmedNullOrEmpty(stringValue)) {
			// Nothing to render
			return;
		}

		Set<String> queryWords = new HashSet<>();

		if (!isTrimmedNullOrEmpty(query)) {
			queryWords = highlighter.split(query);
		}

		HighlightedString value;

		if (html) {
			value = highlighter.highlightSubwordPrefixesWithHtml(stringValue, queryWords);
		} else {
			value = highlighter.highlightSubwordPrefixes(stringValue, queryWords);
		}

		renderHighlighted(value, html, writer);
	}

	private void renderHighlighted(HighlightedString value, boolean html, ResponseWriter writer) throws IOException {
		List<HighlightedString.HighlightSegment> segments = value.getSegments();
		if (segments == null || segments.isEmpty()) {
			// Nothing to render
			return;
		}
		for (HighlightedString.HighlightSegment segment : segments) {
			if (segment.getValue() == null || segment.getValue().isEmpty()) {
				// No content to render (prevent empty highlights!)
				continue;
			}
			if (segment.isHighlighted()) {
				writer.startElement(HIGHLIGHT_TAG, null);
				writer.writeAttribute("class", HIGHLIGHT_CSS_CLASS, null);
			}

			if (html){
				writer.write(segment.getValue());
			} else {
				writer.writeText(segment.getValue(), null);
			}

			if (segment.isHighlighted()) {
				writer.endElement(HIGHLIGHT_TAG);
			}
		}
	}

	private boolean isTrimmedNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
}