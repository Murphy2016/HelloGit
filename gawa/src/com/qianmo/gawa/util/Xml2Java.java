package com.qianmo.gawa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.qianmo.gawa.util.xmljava.Classroom;
import com.qianmo.gawa.util.xmljava.Student;
import com.qianmo.gawa.util.xmljava.Students;

public class Xml2Java {
	
		  
	
	    public static void beanToXML(Object object,String filename) {  
//	        Classroom classroom = new Classroom(1, "软件工程", 4);  
//	        Student student = new Student(101, "张三", 22, classroom); 
//	        
//	        
//	        ArrayList<Student>  list = new ArrayList<Student>(); 
//	        for(int i=0;i<5;i++)
//	        	list.add(student);
//	        Students students = new Students(list);
	        
	        try {  
	            JAXBContext context = JAXBContext.newInstance(object.getClass());  
	            Marshaller marshaller = context.createMarshaller();  
	            marshaller.marshal(object, System.out);
	            
	            //File file = new File("e:/logs/test.xml");
	            //File file = new File("./test.xml");
	            File file = new File(filename);
	            FileOutputStream fop = null;
				try {
					fop = new FileOutputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            marshaller.marshal(object,fop);
	            try {
					fop.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					fop.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        } catch (JAXBException e) {  
	            e.printStackTrace();  
	        }  
	  
	    }  
	      
	      
	    public static void XMLStringToBean(){  
	        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>22</age><classroom><grade>4</grade><id>1</id><name>软件工程</name></classroom><id>101</id><name>张三</name></student>";  
	        try {  
	            JAXBContext context = JAXBContext.newInstance(Student.class);  
	            Unmarshaller unmarshaller = context.createUnmarshaller();  
	            Student student = (Student)unmarshaller.unmarshal(new StringReader(xmlStr));  
	            System.out.println(student.getAge());  
	            System.out.println(student.getClassroom().getName());  
	        } catch (JAXBException e) {  
	            e.printStackTrace();  
	        }  
	          
	    } 
	    
	    public static void main(String args[]){
			 Classroom classroom = new Classroom(1, "软件工程", 4);  
			Student student = new Student(101, "张三", 22, classroom); 
			ArrayList<Student>  list = new ArrayList<Student>(); 
			for(int i=0;i<5;i++)
				list.add(student);
			Students students = new Students(list);
			
	    	beanToXML(students,"./test1.xml");
	    	System.out.println();
	    	System.out.println("----------------seperate line-----------------------");
	    	XMLStringToBean();
	    }
}
