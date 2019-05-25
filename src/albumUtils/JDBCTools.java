package albumUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/*
 * 操作JDBC工具类，封装了一些工具方法
 * 读取配置文件从数据库获取一个连接
 * version1
 * */
public class JDBCTools {
	
	private static DataSource dataSource=null;
	//数据库连接池实例化
	static{
		dataSource=new ComboPooledDataSource("helloc3p0");
	}
	
	
	
/*
 * 处理数据库事务（提交、回滚、开始)
 * */
	public static void comit(Connection connection){
		if(connection!=null){
			try {
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection connection){
		if(connection!=null){
			try {
				connection.rollback();;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void beginTx(Connection connection){
		if(connection!=null){
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	/*
	 * 执行SQL语句，使用PrepareStatement
	 * */
	public static void update(String sql,Object ...args){
		Connection con=null;
		PreparedStatement pre=null;
		try {
			con=getConnection();
			pre=con.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				pre.setObject(i+1,args[i]);
			}
			pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTools.release(pre, con);
		}
		
	}
	
	

	/*
	 * 执行SQL语句，使用Statement
	 * 插入、删除、修改方法
	 * */
	public static void update(String sql){
		Connection conn=null;
		Statement state=null;
		try {
			conn=JDBCTools.getConnection();
			state=conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTools.release(state, conn);
		}
	}
	
	/*
	 * 执行SQL语句，使用Statement
	 * 查询方法
	 * */
	public static ResultSet testResultSet(String sql){
		//获取id=4的customer数据表的记录，并打印
		Connection conn=null;
		Statement state=null;
		ResultSet rs=null;
		
		try {
			//1、获取Connection
			conn=JDBCTools.getConnection();
			//2、获取Statement对象
			state=conn.createStatement();
			
			//3、准备sql
			//String sql="select * from student where id=4";
			
			//4、执行查询，得到结果集
			rs=state.executeQuery(sql);
	       }catch(Exception e){
		e.printStackTrace();
	    }
		return rs;
		}
	/*
	 * 2、关闭数据库资源
	 * */
	public static void release(ResultSet rs,Statement state,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(state != null){
			try {
				state.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(conn !=null){
			try {
				//数据库连接池的Connection对象进行close时
				//并不是真的关闭，而是把该数据库连接归还到连接池
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	/*
	 * 2、关闭数据库资源
	 * */
	public static void release(Statement state,Connection conn){
		if(state != null){
			try {
				state.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(conn !=null){
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
	
/*
 * 1、获取数据库连接
 * */
	
	public static Connection getConnection() throws Exception{
//		//准备连接数据库的四个字符串
//		String driverClass=null;
//		String jdbcUrl=null;
//		String user=null;
//		String password=null;
//		
//		//读取路径下的jdbc.properties文件
//		InputStream in=JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
//		Properties properties=new Properties();
//	    properties.load(in);
//		driverClass=properties.getProperty("driver");
//		jdbcUrl=properties.getProperty("jdbcUrl");
//		user=properties.getProperty("user");
//		password=properties.getProperty("password");
//		
//		//通过反射创建Driver对象
//		//Driver driver=(Driver) Class.forName(driverClass).newInstance();
//		//Properties info=new Properties();
//		//info.put("user", user);
//		//info.put("password",password);
//		//Connection connection=driver.connect(jdbcUrl, info);
//		
//		Class.forName(driverClass);
//		Connection connection=DriverManager.getConnection(jdbcUrl,user,password);
//	
		return dataSource.getConnection();
	}
}
