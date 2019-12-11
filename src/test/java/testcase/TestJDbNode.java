package testcase;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.NodesFactroy.NodeConfig.DataNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.annotation.JTestInject;
import com.jtest.testframe.ITestImpl;
import com.jtest.utility.testlog.JTestLog;
import com.jtest.utility.testlog.TestLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JTestClass.author("leijm")
public class TestJDbNode extends ITestImpl {

	@JTestInject("sqlite")
	JDbNode dbnode;
	@Inject(filename = "DataNode_Event.xml", value = "EMP")
	DataNode dataNode;
	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
	@JTestInject("iparkDev")
	JDbNode iparkDevDb;
	@JTestInject("iparkTest")
	JDbNode iparkTestDb;
	int merchantId=1 ;

	@Override
	public void suiteSetUp() {
		System.out.println(dbnode.toString());
	}


	@Override
	public void suiteTearDown() throws IOException {
		System.out.println("close db :");
		System.out.println(dbnode.toString());
		try {
			dbnode.closeDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void setUp() {
		
	}


	@Override
	public void tearDown() {
	}


	@JTest
	public void test0001_db_checkNum() throws Exception {
		
		dbnode.clearSql().sql("select * from t_user where id=49").getRecordNumber();
		dbnode.checkRecordNumber(1);
	}


	@JTest
	public void test0002_db_checkRecord() throws Exception {
		dbnode.clearSql().sql("select * from t_user where id=49");
		for (Map<String,Object> r: dbnode.select()){
			System.out.println(r);
		}
		dbnode.checkRecord("id=49|name=张三");
	}


	@JTest
	public void test0003_db_checkColumn() throws Exception {
		
		dbnode.clearSql().sql("select * from t_user where id<3");
		dbnode.execSelect().checkColumn("id=1,2|name=张三,张三");
	}
	
	
	@JTest
	public void test0004_dataNode() {
		for (String s : dataNode.all()) {
			System.out.println(s);
		}
		for (Object s : dataNode.allInsert()) {
			System.out.println(s);
		}
		
	}


	@JTest
	public void test0005_dataNode_insert() throws  Exception {
		//dbnode.execSql("create table emp( id char(122),year int,day long )");
		List<String> l=new ArrayList<>();
		for (Object s : dataNode.allInsert()) {
			System.out.println(s);
			dbnode.execSql(s.toString());
			l.add(s.toString());
		}
		dbnode.execSql(l);
		dbnode.clearSql().sql("select * from emp");
		for (Map<String,Object> r: dbnode.select()){
			System.out.println(r);
		}
	}


	@JTestClass.title("test0006_db_checkRecord")
	@JTestClass.pre("create table emp")
	@JTestClass.step("insert record")
	@JTestClass.exp("select record is OK")
	@JTest
	public void test0006_db_checkRecord() throws Exception {
		dbnode.clearSql().sql("select * from t_user where id=49");
		for (Map<String, Object> r : dbnode.select()) {
			JTestLog.logJtest(dbnode.getSql().toString(),r);
		}
		dbnode.checkColumn("id=49|name=张三");
		TestLog.logJtest("jj");
	}
	
	
	@JTestClass.title("test0007_doTest_httpclient")
	@JTestClass.pre("")
	@JTestClass.step("test0007_doTest_httpclient post")
	@JTestClass.exp("ok")
	@JTest
	public void test0007_httpclient() {
		String url = "http://www.2345.com/35035.htm";
		String ret = httpclient.post(url, "", "application/html");
		System.out.println(ret);
		checkEQ(BigDecimal.valueOf(1),BigDecimal.valueOf(1));
	}


	@JTestClass.title("test0008_db")
	@JTestClass.pre("")
	@JTestClass.step("test0008_db post")
	@JTestClass.exp("ok")
	@JTest
	public void test0008_db() throws Exception {
		iparkDevDb.clearSql().appendSql("select count(1) as CNT from p_park_info where merchant_id=?");

		iparkDevDb.appendSql(" and is_deleted=?");
		System.out.println(iparkDevDb.getSql().toString());
		List<Map<String, Object>> rs = iparkDevDb.select(merchantId,1);
		long recordNumber = rs.size();
		System.out.println(rs );

		iparkDevDb.clearSql().appendSql("update p_park_info set id=1111111 where id=0");
		iparkDevDb.execSql();
	}

	@JTest
	@JTestClass.title("previewImage")
	@JTestClass.pre("")
	@JTestClass.step("post http://192.168.222.128:80/rest/template/previewImage")
	@JTestClass.exp("ok postHttpEntity")
	public void test0009_downloadImage() throws UnsupportedEncodingException {
		String url = "http://192.168.222.134:8080/group1/default/20190828/13/31/1/1.jpg";

		byte[] ret = httpclient.postHttpEntity(url, "", "application/json");
		httpclient.checkStatusCode(200);
		System.out.println(url);
		System.out.println(ret);

	}
	public static void main(String[] args) {
		 run(TestJDbNode.class,9);

	}


}
