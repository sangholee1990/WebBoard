package model;

public class BoardVO {

    private int bno;
    private String bname;
    private String btitle;
    private String bpwd;
    private String bcont;
    private int bhit;
    private String bdate;

    public int getBno() {
        return bno;
    }

    public BoardVO setBno(int bno) {
        this.bno = bno;

        return this;
    }

    public String getBname() {
        return bname;
    }

    public BoardVO setBname(String bname) {
        this.bname = bname;

        return this;
    }

    public String getBtitle() {
        return btitle;
    }

    public BoardVO setBtitle(String btitle) {
        this.btitle = btitle;

        return this;
    }

    public String getBpwd() {
        return bpwd;
    }

    public BoardVO setBpwd(String bpwd) {
        this.bpwd = bpwd;

        return this;
    }

    public String getBcont() {
        return bcont;
    }

    public BoardVO setBcont(String bcont) {
        this.bcont = bcont;

        return this;
    }

    public int getBhit() {
        return bhit;
    }

    public BoardVO setBhit(int bhit) {
        this.bhit = bhit;

        return this;
    }

    public String getBdate() {
        return bdate;
    }

    public BoardVO setBdate(String bdate) {
        this.bdate = bdate;

        return this;
    }

    @Override
    public String toString() {
        return "BoardVO [bno=" + bno + ", bname=" + bname + ", btitle=" + btitle + ", bpwd=" + bpwd + ", bcont=" + bcont
                + ", bhit=" + bhit + ", bdate=" + bdate + "]";
    }
}
