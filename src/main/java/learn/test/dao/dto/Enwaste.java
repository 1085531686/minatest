package learn.test.dao.dto;

import java.util.Date;

public class Enwaste extends EnwasteKey {
    private Date ENTIME;

    private String EntimeStr ;
    
    private String ENSTATION;

    private String ENLANE;


    private String VLP;

    private String IMAGEFILENAME;

   

    public Date getENTIME() {
        return ENTIME;
    }

    public void setENTIME(Date ENTIME) {
        this.ENTIME = ENTIME;
    }

    public String getENSTATION() {
        return ENSTATION;
    }

    public void setENSTATION(String ENSTATION) {
        this.ENSTATION = ENSTATION == null ? null : ENSTATION.trim();
    }

    public String getENLANE() {
        return ENLANE;
    }

    public void setENLANE(String ENLANE) {
        this.ENLANE = ENLANE == null ? null : ENLANE.trim();
    }

   


	public String getEntimeStr() {
		return EntimeStr;
	}

	public void setEntimeStr(String entimeStr) {
		EntimeStr = entimeStr;
	}

	public String getVLP() {
		return VLP;
	}

	public void setVLP(String vLP) {
		VLP = vLP;
	}

	public String getIMAGEFILENAME() {
		return IMAGEFILENAME;
	}

	public void setIMAGEFILENAME(String iMAGEFILENAME) {
		IMAGEFILENAME = iMAGEFILENAME;
	}

	@Override
	public String toString() {
		return "Enwaste [ENLANE=" + ENLANE + ", ENSTATION=" + ENSTATION
				+ ", ENTIME=" + ENTIME + ", EntimeStr=" + EntimeStr + ", VLP="
				+ VLP + "IMAGEFILENAME=" +IMAGEFILENAME +"]";
	}
    
}