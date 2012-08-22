package jobs.mockups;

import play.exceptions.UnexpectedException;
import play.jobs.Job;

import java.io.File;

import static java.io.File.createTempFile;

public class ThumbnailRenderer extends Job<File> {

    private final String url;

    public ThumbnailRenderer(String url) {
        this.url = url;
    }

    @Override
    public File doJobWithResult() {
        try {
            String phantomJsExecutable = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/bin/phantomjs";
            String rasterizeScript = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/examples/rasterize.js";
            File tmpFile = createTempFile("mockup", ".png");
            new ProcessBuilder(phantomJsExecutable, rasterizeScript, url, tmpFile.getAbsolutePath()).start().waitFor();
            return tmpFile;
        } catch (Exception e) {
            play.Logger.error("Failed to generate thumbnail for url " + url, e);
            throw new UnexpectedException(e);
        }
    }

}
