package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel per1;
	private static PersonDomainModel per2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1972-07-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Matt");
		per1.setLastName("Billone");
		per1.setBirthday(dDate);
		per1.setCity("Downingtown");
		per1.setPostalCode(19335);
		per1.setStreet("106 Nicolson Drive");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDomainModel per = null;
		ArrayList<PersonDomainModel> pers = PersonDAL.getPersons();
		if (pers.size() >= 1){
			for (PersonDomainModel p:pers){
				PersonDAL.deletePerson(p.getPersonID());
				per = PersonDAL.getPerson(p.getPersonID());
				assertNull("The person should not be in the database", per);
			}
		}
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per = null;
		try{
			PersonDAL.deletePerson(per1.getPersonID());
			per = PersonDAL.getPerson(per1.getPersonID());
			assertNull("The Person shouldn't have been in the database",per);		
		}
		catch (IllegalArgumentException e){
			assertNull("There shoud be no one in the database now", per);
		}
		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Name Didn't Change",per1.getLastName() == C_LASTNAME);
		assertFalse("Name has been changed", per1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
		
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
	}
	
	@Test
	public void getPersonsTest() {
		PersonDomainModel per;
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("There should be nobody in the database", per);
		PersonDAL.addPerson(per1);

		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("A person should've been added to the database", per);
		PersonDAL.addPerson(per2);

		per = PersonDAL.getPerson(per2.getPersonID());
		System.out.println(per1.getPersonID() + " also found");
		assertNotNull("Another person should have been added to the database", per);

		pers = PersonDAL.getPersons();
		assertTrue("There should be people in the database", pers.size() >= 1);
	
}
