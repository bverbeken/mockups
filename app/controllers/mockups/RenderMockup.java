package controllers.mockups;

import models.mockups.Mockup;
import play.exceptions.UnexpectedException;
import play.mvc.Http;
import play.mvc.results.Result;

public class RenderMockup extends Result {

    private Mockup mockup;
    private boolean enhance;

    public RenderMockup(Mockup mockup, boolean enhance) {
        this.mockup = mockup;
        this.enhance = enhance;
    }

    public void apply(Http.Request request, Http.Response response) {
        try {
            response.out.write(mockup.getContent(enhance).getBytes(getEncoding()));
            setContentTypeIfNotSet(response, mockup.getContentType());
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }


}
