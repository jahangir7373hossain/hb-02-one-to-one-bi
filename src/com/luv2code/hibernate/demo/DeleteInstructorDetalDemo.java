package com.luv2code.hibernate.demo;


import java.text.ParseException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;



public class DeleteInstructorDetalDemo {

	public static void main(String[] args) throws ParseException {
	
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int getId = 3;
			
			InstructorDetail tempIntructorDetail = session.get(InstructorDetail.class, getId);
			
			//print the instructor detail
			System.out.println("tempIntructorDetail: " + tempIntructorDetail);
			
			// print the associate instructor
			System.out.println("The associated instructor: " + tempIntructorDetail.getInstructor());
			
			
			//Now delete the instructor detail
			System.out.println("Deleting tempIntructorDetail: " + tempIntructorDetail);
			
			//remove the associate object reference
			//break bi-directional link
			session.delete(tempIntructorDetail);
			
			tempIntructorDetail.getInstructor().setInstructorDetail(null);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//Handle connection leak issue
			session.close();
			
			factory.close();
		}

	}

}
