package com.qianmo.gawa.netlog;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "netlogs")  
@XmlAccessorType(XmlAccessType.NONE)
public class Netlogs {
	@XmlElement(name = "netlog")  
    private List<Netlog> netlogList;

	public Netlogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Netlogs(List<Netlog> netlogList) {
		super();
		this.netlogList = netlogList;
	}

	public List<Netlog> getNetlogList() {
		return netlogList;
	}

	public void setNetlogList(List<Netlog> netlogList) {
		this.netlogList = netlogList;
	}
	
	
	
	
}
