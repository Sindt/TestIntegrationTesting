package cphbusiness.test.sp.integration.persistence;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MysqlServiceTest {

	MysqlService service = new MysqlService("jdbc:mysql://localhost:3306/SocialNetwork", "root", "pwd",
			"com.mysql.jdbc.Driver");

	IDatabaseConnection dbConnection;

	@Before
	public void setUp() throws Exception {
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
	public void test() {
		try {
			IDataSet xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
			IDataSet databaseDataSet = new QueryDataSet(dbConnection);
			// Assertion.assertEquals(xmlTable, databaseTable);

		} catch (DataSetException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
