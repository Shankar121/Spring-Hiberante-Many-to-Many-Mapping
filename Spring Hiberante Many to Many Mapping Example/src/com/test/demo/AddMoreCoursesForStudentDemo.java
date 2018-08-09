package com.test.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.entity.Course;
import com.test.entity.Instructor;
import com.test.entity.InstructorDetail;
import com.test.entity.Review;
import com.test.entity.Student;

public class AddMoreCoursesForStudentDemo {

	
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
			Student student=session.get(Student.class, 1);
			Course tempCourse1=new Course("Singing Tutorial");
			Course tempCourse2=new Course("Dancing Tutorial");
			tempCourse1.addStudent(student);
			tempCourse2.addStudent(student);
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.getTransaction().commit();
			System.out.println("done");
		}catch(Exception ex) {
			System.out.println(ex);
		}finally {
			factory.close();
			
		}
	}
	
}
