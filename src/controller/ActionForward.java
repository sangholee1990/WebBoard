package controller;

import java.io.Serializable;

public class ActionForward implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean redirect = false;
    private String path = "";
    private String urlNama = null;
    private String className = null;
    private Object returnData = null;

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrlNama() {
        return urlNama;
    }

    public void setUrlNama(String urlNama) {
        this.urlNama = urlNama;
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

    @Override
    public String toString() {
        return "ActionForward [redirect=" + redirect + ", path=" + path + ", urlNama=" + urlNama + ", className="
                + className + ", returnData=" + returnData + "]";
    }
}
