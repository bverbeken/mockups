package models.mockups;

import play.Play;
import play.templates.Template;
import play.templates.TemplateLoader;
import play.vfs.VirtualFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static play.Play.getVirtualFile;
import static utils.MockupsProperties.getMockupPath;

public class Mockup implements Comparable<Mockup>{

    private VirtualFile virtualFile;

    private Mockup(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
    }

    public String getName() {
        return virtualFile.getName();
    }

    public String getPath() {
        return virtualFile.relativePath().substring(9);
    }

    public Boolean isDirectory() {
        return virtualFile.isDirectory();
    }

    public Template getTemplate() {
        return TemplateLoader.load(virtualFile);
    }

    public static List<Mockup> allMockups() {
        return allMockups("");
    }

    public static List<Mockup> allMockups(String relativeDirectory) {
        List<Mockup> result = new ArrayList<Mockup>();
        for (VirtualFile virtualFile : getVirtualFile(getMockupPath() + relativeDirectory).list()) {
            result.add(new Mockup(virtualFile));
        }
        Collections.sort(result);
        return result;
    }

    public static Mockup mockupByName(String m) {
        String path = getMockupPath() + m;
        VirtualFile virtualFile = Play.getVirtualFile(path);
        if (virtualFile == null) {
            return null;
        } else {
            return new Mockup(virtualFile);
        }
    }

    @Override
    public int compareTo(Mockup that) {
        int result = that.isDirectory().compareTo(this.isDirectory());
        if (result == 0){
            result = this.getName().compareTo(that.getName());
        }
        return result;
    }
}
