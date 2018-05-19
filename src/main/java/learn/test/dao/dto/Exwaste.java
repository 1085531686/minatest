package learn.test.dao.dto;

import java.util.Date;

public class Exwaste {

	private String WASTEID;

	private Short MODIFYFLAG;

	private Date EXTIME;

	private String ExtimeStr;

	private Date ENTIME;

	private String EntimeStr;
	private String EXSTATION;

	private String EXLANE;

	private String ENSTATION;

	private String ENLANE;

	private String VLP;
	private String ENVLP;
	private String IMAGEFILENAME;
	public String getWASTEID() {
		return WASTEID;
	}
	public void setWASTEID(String wASTEID) {
		WASTEID = wASTEID;
	}
	public Short getMODIFYFLAG() {
		return MODIFYFLAG;
	}
	public void setMODIFYFLAG(Short mODIFYFLAG) {
		MODIFYFLAG = mODIFYFLAG;
	}
	public Date getEXTIME() {
		return EXTIME;
	}
	public void setEXTIME(Date eXTIME) {
		EXTIME = eXTIME;
	}
	public String getExtimeStr() {
		return ExtimeStr;
	}
	public void setExtimeStr(String extimeStr) {
		ExtimeStr = extimeStr;
	}
	public Date getENTIME() {
		return ENTIME;
	}
	public void setENTIME(Date eNTIME) {
		ENTIME = eNTIME;
	}
	public String getEntimeStr() {
		return EntimeStr;
	}
	public void setEntimeStr(String entimeStr) {
		EntimeStr = entimeStr;
	}
	public String getEXSTATION() {
		return EXSTATION;
	}
	public void setEXSTATION(String eXSTATION) {
		EXSTATION = eXSTATION;
	}
	public String getEXLANE() {
		return EXLANE;
	}
	public void setEXLANE(String eXLANE) {
		EXLANE = eXLANE;
	}
	public String getENSTATION() {
		return ENSTATION;
	}
	public void setENSTATION(String eNSTATION) {
		ENSTATION = eNSTATION;
	}
	public String getENLANE() {
		return ENLANE;
	}
	public void setENLANE(String eNLANE) {
		ENLANE = eNLANE;
	}
	public String getVLP() {
		return VLP;
	}
	public void setVLP(String vLP) {
		VLP = vLP;
	}
	public String getENVLP() {
		return ENVLP;
	}
	public void setENVLP(String eNVLP) {
		ENVLP = eNVLP;
	}
	public String getIMAGEFILENAME() {
		return IMAGEFILENAME;
	}
	public void setIMAGEFILENAME(String iMAGEFILENAME) {
		IMAGEFILENAME = iMAGEFILENAME;
	}
	@Override
	public String toString() {
		return "Exwaste [ENLANE=" + ENLANE + ", ENSTATION=" + ENSTATION
				+ ", ENTIME=" + ENTIME + ", ENVLP=" + ENVLP + ", EXLANE="
				+ EXLANE + ", EXSTATION=" + EXSTATION + ", EXTIME=" + EXTIME
				+ ", EntimeStr=" + EntimeStr + ", ExtimeStr=" + ExtimeStr
				+ ", IMAGEFILENAME=" + IMAGEFILENAME + ", VLP=" + VLP + "]";
	}
	
	
}
