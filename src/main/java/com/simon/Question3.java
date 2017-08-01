package com.simon;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author simon sew
 *
 * Note : what does it mean by independent method? Does it mean static methods?
 * 
 * Idea : 
 * 1. Basically before invoking the method. Add a timestamp
 * 2. After invoking the method. Calculate the differences.
 * 3. You will get your average time per method call.
 * 
 */
public class Question3 {

	public Method[] getStaticMethods(Class<?> c) {
	    Method[] all = c.getDeclaredMethods();
	    List<Method> staticMethods = new ArrayList<Method>();

	    for (Method m : all) {
	        if (Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
	        	staticMethods.add(m);
	        }
	    }

	    return staticMethods.toArray(new Method[staticMethods.size()]);
	}    
	
	public void calculateTotalTime(Class _class,Method method,Object[] params) throws Exception {
		
//		System.out.println(params.length);
		
		long currentTime=System.currentTimeMillis();
		System.out.println("Method return : " + method.invoke(_class, params));
		long elapsedTime = (System.currentTimeMillis() -  currentTime);
		System.out.println("Total time taken to invoke " + method.getName() + " is " + elapsedTime);
		System.out.println("----------------------------------------------");

	}
	
	public static void main(String[] args) {
		
		Class _class = Math.class;
		Method[] staticListMethod = new Question3().getStaticMethods(_class);
		
		Question3 question3Test = new Question3();	
		
		for(Method method:staticListMethod) {
			System.out.println("Method name : " + method + ",param count : " + method.getParameterCount());
			
			try {
				if(method.getParameterCount() > 0) {
				
					List<Object> params = new ArrayList<Object>();
					Type[] gpType = method.getGenericParameterTypes();
//					Parameter parameters[]=method.getParameters();								
					for(Type param:gpType) {
//						System.out.println(param.getTypeName());
						if(param.getTypeName().equals("int")) {
							params.add(3);
						} else if(param.getTypeName().equals("double")) {
							params.add(3.3D);
						} else if(param.getTypeName().equals("float")) {
							params.add(3.8F);
						} else if(param.getTypeName().equals("long")) {
							params.add(40L);
						}
					}
					
					question3Test.calculateTotalTime(_class,method,params.toArray(new Object[params.size()]));				
					
				} else if(method.getParameterCount() == 0) {
					question3Test.calculateTotalTime(_class,method,new Object[]{});				
				}	
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}
}
