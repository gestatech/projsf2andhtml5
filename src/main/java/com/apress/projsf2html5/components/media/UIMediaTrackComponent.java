package com.apress.projsf2html5.components.media;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

/**
 * Composite component for the {@code <track/>} element.
 */
@FacesComponent("UIMediaTrackComponent")
public class UIMediaTrackComponent extends UINamingContainer {

    private static final String ELEMENT_ID = "track";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_DEFAULT = "default";
    private static final String ATTRIBUTE_KIND = "kind";
    private static final String ATTRIBUTE_SRCLANG = "srclang";

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        super.encodeBegin(context);
        UIComponent element = findElement();

        MediaTrack mediaTrack = (MediaTrack) getValueExpression(ATTRIBUTE_VALUE)
                .getValue(getFacesContext().getELContext());
        
        Map<String, Object> passThrough = element.getPassThroughAttributes();
        
        if (mediaTrack.isDefaultTrack()) {
            passThrough.put(ATTRIBUTE_DEFAULT, ATTRIBUTE_DEFAULT);
        }

        if (mediaTrack.getKind() != null) {
            passThrough.put(ATTRIBUTE_KIND, mediaTrack.getKind().toString());
        }

        if (mediaTrack.getLocale() != null) {
            passThrough.put(ATTRIBUTE_SRCLANG, mediaTrack.getLocale().toString());
        }
    }

    public String getElementId() {
        return ELEMENT_ID;
    }

    private UIComponent findElement() throws IOException {
        UIComponent element = findComponent(getElementId());

        if (element == null) {
            throw new IOException("Track element with ID "
                    + getElementId() + " could not be found");
        }
        return element;
    }
}
