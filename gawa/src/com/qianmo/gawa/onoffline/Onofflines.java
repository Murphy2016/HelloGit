package com.qianmo.gawa.onoffline;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "onofflines")  
@XmlAccessorType(XmlAccessType.NONE)
public class Onofflines {
	@XmlElement(name = "onoffline")  
    private List<Onoffline> onofflineList;

	public Onofflines() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Onofflines(List<Onoffline> onofflineList) {
		super();
		this.onofflineList = onofflineList;
	}

	public List<Onoffline> getOnofflineList() {
		return onofflineList;
	}

	public void setOnofflineList(List<Onoffline> onofflineList) {
		this.onofflineList = onofflineList;
	}
	
	
	
	
	
}
