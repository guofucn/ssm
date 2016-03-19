package ssm;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jc.ssm.controller.Hello;
import com.jc.ssm.dao.UserDao;
import com.jc.ssm.model.User;
import com.jc.ssm.service.UserService;

public class TestConfig {
	@Test
	public void TestJdbc() {
		//测试通过表示JDBC配置成功
		String url = "jdbc:mysql://localhost/test";
		String user = "root";
		String password = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user_t");
			System.out.println("connect success");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void TestMybatis() throws IOException {
		//测试通过表示Mybatis配置成功
		String resource = "configs/mybatis.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);

		SqlSession session = factory.openSession();
		UserDao userDao = session.getMapper(UserDao.class);
		User user = userDao.getById(1);
		System.out.println(user.getPassword());
		
		session.commit();
		session.close();
	}
	
	@Test
	public void TestSpringMybatis(){
		//测试spring和Mybatis整合,通过spring获取dao
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("configs/spring-mybatis.xml");
		
		UserDao dao = (UserDao) ac.getBean("userDao");
		User user = dao.getById(1);
		System.out.println(user.getPassword());
		ac.close();
	}
	
	@Test
	public void TestSpringService(){
		//测试spring生成service,注入dao
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("configs/spring-mybatis.xml");
		UserService userServ = (UserService) ac.getBean("userService");
		User user = userServ.getUserById(1);
		System.out.println(user.getPassword());
		ac.close();
	}
	
	@Test
	public void TestSpringMVC(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("configs/spring-mybatis.xml");
		Hello hello = (Hello)ac.getBean("hello");
		hello.test();
		ac.close();
	}

}
