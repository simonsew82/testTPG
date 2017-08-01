package com.simon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author simon sew
 *
 */
public class Question2 {
	
	class GPA {

		private int ID=0;
		private String name;
		private float gcpa=0F;
		
		GPA(int ID,String name,float gcpa) {
			this.ID=ID;
			this.name=name;
			this.gcpa=gcpa;
		}
		
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public float getGcpa() {
			return gcpa;
		}
		public void setGcpa(float gcpa) {
			this.gcpa = gcpa;
		}
	}
	
	private List<GPA> generateSamples() {
		List<GPA> list = new ArrayList<GPA>();
		
		list.add(new GPA(33,"Tina",3.68F));
		list.add(new GPA(85,"Louis",3.85F));
		list.add(new GPA(56,"Samil",3.75F));
		list.add(new GPA(19,"Samar",3.75F));
		list.add(new GPA(22,"Lorry",3.76F));
		
		//extra
		list.add(new GPA(45,"Samad",3.75F));
		list.add(new GPA(95,"Simon",3.12F));

		//unique id
		list.add(new GPA(95,"Simon Duplicate",3.33F));
		
		return list;
	}
	
	/**
	 * Remove duplicate id
	 * 
	 * @param list
	 * @return
	 */
	public List<GPA> removeDuplicate(List<GPA> list) {
		List<GPA> filteredList = new ArrayList<GPA>();
		Set<Integer> uniqueId = new HashSet<Integer>();
		
		for(GPA gpa:list) {
			if(!uniqueId.contains(gpa.getID())) {
				//add to unique id container
				uniqueId.add(gpa.getID());
				//add to a new list
				filteredList.add(gpa);
			}
		}
	
		return filteredList;
	}
	
	public static void main(String[] args) {
		Question2 test = new Question2();
		List<GPA> list = test.generateSamples();
		
		//remove duplicated unique id
		list = test.removeDuplicate(list);
		
		//perform sorting
		Collections.sort(list, new Comparator<GPA>() {  
		    public int compare(GPA p1, GPA p2) {  

		        //check by gcpa
		        int sizeCmp = Float.compare(p1.getGcpa(),p2.getGcpa());  
		        if (sizeCmp > 0) {  
		            return -1;  
		        //if cgpa is the same    
		        } else if(sizeCmp == 0) {
			    	//check by name 
		        	return p1.getName().compareTo(p2.getName());  			        
		        }

		        return -1;  
		    }  
		}); 
		
		for(GPA gpa : list) {
//			System.out.println(gpa.getID() + " " + gpa.getGcpa() + " " + gpa.getName());
			System.out.println(gpa.getName());
		}
	}
}
