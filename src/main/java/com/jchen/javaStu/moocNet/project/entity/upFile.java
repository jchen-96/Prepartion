package com.jchen.javaStu.moocNet.project.entity;


/**
 * Created by jchen on 17-9-1.
 */
public class upFile {
    private int id;

    private String fileName;

    private byte[] FileContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return FileContent;
    }

    public void setFileContent(byte[] fileContent) {
        FileContent = fileContent;
    }

    @Override
    public String toString() {
        return "upFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", 文件大小为=" + getFileContent().length / 1024 + "KB" +
                '}';
    }
}
