package controllers.mockups;

import models.mockups.Mockup;
import play.exceptions.UnexpectedException;
import play.mvc.Http;
import play.mvc.results.Result;

public class RenderMockup extends Result {

    private Mockup mockup;

    public RenderMockup(Mockup mockup) {
        this.mockup = mockup;
    }

    public void apply(Http.Request request, Http.Response response) {
        try {
            response.out.write(mockup.getContent().getBytes(getEncoding()));
            setContentTypeIfNotSet(response, mockup.getContentType());
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }


}
