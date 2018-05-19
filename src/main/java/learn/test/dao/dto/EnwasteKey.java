package learn.test.dao.dto;

public class EnwasteKey {
    private String WASTEID;

    private Short MODIFYFLAG;

    public String getWASTEID() {
        return WASTEID;
    }

    public void setWASTEID(String WASTEID) {
        this.WASTEID = WASTEID == null ? null : WASTEID.trim();
    }

    public Short getMODIFYFLAG() {
        return MODIFYFLAG;
    }

    public void setMODIFYFLAG(Short MODIFYFLAG) {
        this.MODIFYFLAG = MODIFYFLAG;
    }
}