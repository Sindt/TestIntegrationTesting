package cphbusiness.test.sp.integration.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import cphbusiness.test.sp.integration.entity.User;

public class MysqlServiceTest {

	@Mock
	MysqlService service = new MysqlService("jdbc:mysql://localhost:3306/SocialNetwork", "root", "pwd",
			"com.mysql.jdbc.Driver");

	IDatabaseConnection dbConnection;
	private List<User> users = new ArrayList();

	@Before
	public void setUp() throws Exception {
		users.add(new User(1, "joe", "POTUS", "01-01-2012"));
		try {
			dbConnection = new DatabaseConnection(service.getConnection());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void dbUnitTest() {
		try {
			IDataSet xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("data\\dataset.xml"));
			IDataSet databaseDataSet = new QueryDataSet(dbConnection);
			ITable table = databaseDataSet.getTable("nodes");

			// Assertion.assertEquals(xmlTable, databaseTable);

		} catch (DataSetException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void mockitoTest() {

		when(service.getAllUsers()).thenReturn(users);
		List<User> result = service.getAllUsers();
		assertThat(result, is(users));

	}

}
