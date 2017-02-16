package com.qianmo.gawa.util.xmljava;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")  
@XmlAccessorType(XmlAccessType.NONE)
public class Students {

	 
		public Students() {
			super();
		}
	  
	    public Students(List<Student> studentList) {
			super();
			this.studentList = studentList;
		}

		@XmlElement(name = "student")  
	    private List<Student> studentList;  
	  
	    public List<Student> getStudentList() {  
	        return studentList;  
	    }  
	  
	    public void setStudentList(List<Student> studentList) {  
	        this.studentList = studentList;  
	    }  
	  
	

}
