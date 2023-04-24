package model;

public class FileVO {

    private  String fileInfo;

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "fileInfo='" + fileInfo + '\'' +
                '}';
    }
}
