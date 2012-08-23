package models.mockups;

import play.Play;
import play.libs.IO;
import play.libs.MimeTypes;
import play.templates.TemplateLoader;
import play.vfs.VirtualFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang.StringUtils.removeEnd;
import static play.Play.getVirtualFile;
import static utils.MockupsProperties.getMockupPath;

public class Mockup implements Comparable<Mockup> {

    private VirtualFile virtualFile;

    private Mockup(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
    }

    public String getFileName() {
        return virtualFile.getName();
    }

    public String getPath() {
        return virtualFile.relativePath();
    }

    public Boolean isDirectory() {
        return virtualFile.isDirectory();
    }

    public String getContent(boolean enhance) {
        String originalContent = TemplateLoader.load(virtualFile).render();
        if (enhance && getFileName().endsWith(".html")) {
            return enhanceHtml(originalContent);
        }
        return originalContent;
    }

    private String enhanceHtml(String originalContent) {
        String extraInfo = IO.readContentAsString(Play.getVirtualFile("/app/views/mockups/Mockups/extraInfo.html").getRealFile());
        return originalContent + extraInfo;
    }

    public String getTitle() {
        return removeEnd(getFileName(), ".html");
    }


    public static List<Mockup> allMockups() {
        return allMockups(getMockupPath());
    }

    public static List<Mockup> allMockups(String relativeDirectory) {
        List<Mockup> result = new ArrayList<Mockup>();
        for (VirtualFile virtualFile : getVirtualFile(relativeDirectory).list()) {
            result.add(new Mockup(virtualFile));
        }
        Collections.sort(result);
        return result;
    }

    public static Mockup mockupByPath(String path) {
        VirtualFile virtualFile = Play.getVirtualFile(path);
        return virtualFile == null ? null : new Mockup(virtualFile);
    }

    public String getContentType() {
        return MimeTypes.getContentType(getFileName(), "text/plain");
    }

    @Override
    public int compareTo(Mockup that) {
        int result = that.isDirectory().compareTo(this.isDirectory());
        if (result == 0) {
            result = this.getFileName().compareTo(that.getFileName());
        }
        return result;
    }


}
