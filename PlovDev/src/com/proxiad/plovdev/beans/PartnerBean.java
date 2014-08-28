package com.proxiad.plovdev.beans;

public class PartnerBean {
	private int portraitId;
	private String urlLink;
	
	public PartnerBean(int portraitId, String urlLink) {
		super();
		this.portraitId = portraitId;
		this.urlLink = urlLink;
	}
	
	public int getPortraitId() {
		return portraitId;
	}

	public void setPortraitId(int portraitId) {
		this.portraitId = portraitId;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
}