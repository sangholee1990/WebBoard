package controller;

import java.io.Serializable;

public class ActionForward implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isRedirect = false;
    private String path = "";
    private String urlName = null;
    private String className = null;
    private Object returnData = null;
    private String title = null;
    private boolean isJson = false;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isJson() {
        return isJson;
    }

    public void setJson(boolean json) {
        isJson = json;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    @Override
    public String toString() {
        return "ActionForward{" +
                "isRedirect=" + isRedirect +
                ", path='" + path + '\'' +
                ", urlName='" + urlName + '\'' +
                ", className='" + className + '\'' +
                ", returnData=" + returnData +
                ", title='" + title + '\'' +
                ", isJson=" + isJson +
                '}';
    }
}
