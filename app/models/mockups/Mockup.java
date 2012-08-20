package models.mockups;

import play.Play;
import play.templates.Template;
import play.templates.TemplateLoader;
import play.vfs.VirtualFile;

import java.util.ArrayList;
import java.util.List;

import static play.Play.getVirtualFile;
import static utils.MockupsProperties.getMockupPath;

public class Mockup {

    private VirtualFile virtualFile;

    private Mockup(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
    }

    public Mockup(String mockupName) {
        String path = getMockupPath() + mockupName;
        this.virtualFile = Play.getVirtualFile(path);
        if (virtualFile == null) {
            throw new IllegalArgumentException("mockup [" + path + "] does not exist");
        }
    }

    public String getName() {
        return virtualFile.getName();
    }

    public String getPath() {
        return virtualFile.relativePath().substring(9);
    }

    public boolean isDirectory(){
        return virtualFile.isDirectory();
    }

    public Template getTemplate() {
        return TemplateLoader.load(getFullPath());
    }

    public String getFullPath() {
        return getMockupPath() + getPath();
    }

    public static List<Mockup> allMockups(){
        return allMockups("");
    }

    public static List<Mockup> allMockups(String relativeDirectory) {
        List<Mockup> result = new ArrayList<Mockup>();
        for (VirtualFile virtualFile : getVirtualFile(getMockupPath() + relativeDirectory).list()) {
            result.add(new Mockup(virtualFile));
        }
        return result;
    }

}
