package com.test.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.entity.Course;
import com.test.entity.Instructor;
import com.test.entity.InstructorDetail;
import com.test.entity.Review;
import com.test.entity.Student;

public class CreateCourseAndStudentsDemo {

	
	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		try {
		
			session.beginTransaction();
			Course tempCourse=new Course("Java Tutorial");
			session.save(tempCourse);
			Student student1=new Student("Shankar", "Gupta", "shankkr9@gmail.com");
			Student student2=new Student("Puja", "Gupta", "pujagupta9@gmail.com");
			session.save(student1);
			session.save(student2);
			tempCourse.addStudent(student1);
			tempCourse.addStudent(student2);
			session.getTransaction().commit();
			System.out.println("done");
		}catch(Exception ex) {
			System.out.println(ex);
		}finally {
			factory.close();
			
		}
	}
	
}
