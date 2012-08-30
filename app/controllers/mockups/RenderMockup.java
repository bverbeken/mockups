package controllers.mockups;

import models.mockups.Mockup;
import play.exceptions.UnexpectedException;
import play.mvc.Http;
import play.mvc.results.Result;

public class RenderMockup extends Result {

    private Mockup mockup;
    private boolean showRibbon;

    public RenderMockup(Mockup mockup, boolean showRibbon) {
        this.mockup = mockup;
        this.showRibbon = showRibbon;
    }

    public void apply(Http.Request request, Http.Response response) {
        try {
            response.out.write(mockup.getContent(showRibbon).getBytes(getEncoding()));
            setContentTypeIfNotSet(response, mockup.getContentType());
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }


}
