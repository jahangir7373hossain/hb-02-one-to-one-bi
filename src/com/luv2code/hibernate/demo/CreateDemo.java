package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateDemo {

	public static void main(String[] args) throws ParseException {
	
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//Create the objects
			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com", "Luv 2 code!!!");
			
//	        Instructor tempInstructor = new Instructor("Madhu", "Patel", "Madhu@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com", "Guitar");
			
			
			//Associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();						
			
			// save the instructor
			//Note:This will also save the details object
			//because of cascade type all
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		finally {
			factory.close();
		}

	}

}
