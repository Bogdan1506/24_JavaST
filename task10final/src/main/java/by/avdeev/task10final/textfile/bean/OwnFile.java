package by.avdeev.task10final.textfile.bean;

import java.util.Objects;

public class OwnFile {
    private Directory parent;
    private String child;
    private java.io.File file;

    public OwnFile(Directory parent, String child) {
        this.parent = parent;
        this.child = child;
        this.file = new java.io.File(parent.getPath(), child);
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public java.io.File getFile() {
        return file;
    }

    public void setFile(java.io.File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "File{" +
                "path=" + parent +
                ", name='" + child + '\'' +
                ", file=" + file +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnFile ownFile = (OwnFile) o;
        return Objects.equals(parent, ownFile.parent) &&
                Objects.equals(child, ownFile.child) &&
                Objects.equals(file, ownFile.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, child, file);
    }
}
