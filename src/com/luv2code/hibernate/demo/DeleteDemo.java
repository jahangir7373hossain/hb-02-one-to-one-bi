package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;


public class DeleteDemo {

	public static void main(String[] args) throws ParseException {
	
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor by primary key
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found instructor: " + tempInstructor);
			
			//Delete the instructor
			
			if(tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				
				//Note: will also delete associate details object
				//because of cascadeType all
				session.delete(tempInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		finally {
			factory.close();
		}

	}

}
