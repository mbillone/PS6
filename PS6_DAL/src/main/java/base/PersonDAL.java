package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		try{
			ts = session.beginTransaction();
			session.save(per);
			ts.commit();
		}
		catch (HibernateException e) {
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return per;
	}

	public static ArrayList<PersonDomainModel> getPersons() {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		ArrayList<PersonDomainModel> people = ArrayList<PersonDomainModel>();
		try{
			ts = session.beginTransaction();
			List persons = session.createQuery("from PersonDomainModel").list();
			for(Iterator iterate = persons.iterator(); iterator.hasNext();){
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				pers.add(per);
			}
			ts.commit();
		}
		catch (HibernateException e){
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return people;

	}

	public static PersonDomainModel getPerson(UUID perID) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		PersonDomainModel getPer = null;
		try{
			ts = session.beginTransaction();
			Query query = session.createQuery("from PersonDomainModel where personID = id");
			
			List<?> list = query.list();
			if (list.size() != 0){
				getPer = (PersonDomainModel) list.get(0);		
			}
			else{
				getPer = null;
			}
			ts.commit();
		}
		catch (HibernateException e){
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return getPer;
	}

	public static void deletePerson(UUID perID) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		try{
			ts = session.beginTransaction();
			
			PersonDomainModel per = (PersonDomainModel) session.get(PersonDomainModel.class, perID);
			session.delete(per);
			ts.commit();
		}
		catch (HibernateException e){
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		try{
			ts = session.beginTransaction();
			session.update(per);
			ts.commit();
		}
		catch (HibernateException e){
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return per;
	}
}