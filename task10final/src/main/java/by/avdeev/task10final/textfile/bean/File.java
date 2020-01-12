package by.avdeev.task10final.textfile.bean;

import by.avdeev.task10final.textfile.bean.exception.FileException;

import java.io.IOException;
import java.util.Objects;

public class File {
    private Directory path;
    private String name;
    private java.io.File textFile;

    public File(Directory path, String name) {
        this.path = path;
        this.name = name;
        this.textFile = new java.io.File(path.toString(), name);
    }

    public boolean rename(String dest) {
        name = dest;
        java.io.File newFile = new java.io.File(path.toString(), name + ".txt");
        return textFile.renameTo(newFile);
    }

    public boolean createNewFile() throws FileException {
        try {
            return textFile.createNewFile();
        } catch (IOException e) {
            throw new FileException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getPath() {
        return path;
    }

    public void setPath(Directory path) {
        this.path = path;
    }

    public java.io.File getTextFile() {
        return textFile;
    }

    public void setTextFile(java.io.File textFile) {
        this.textFile = textFile;
    }

    @Override
    public String toString() {
        return "File{" +
                "path=" + path +
                ", name='" + name + '\'' +
                ", file=" + textFile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(path, file.path) &&
                Objects.equals(name, file.name) &&
                Objects.equals(textFile, file.textFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, textFile);
    }
}
