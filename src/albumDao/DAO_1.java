package albumDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/*
 * DAO处理实体类的类型
 * */
public interface DAO_1 <T> {
	
	//更新
void update(Connection connection,String sql,Object ...args) throws SQLException;

//返回一个T类型对象
T get(Connection connection,String sql,Object ...args) throws SQLException;

//返回一个T类型集合
List<T> getForList(Connection connection,String sql,Object ...args) throws SQLException;

//返回某一个值
<E> E getForValue(Connection connection,String sql,Object ...args) throws SQLException;

//执行一批SQL插入、更新或删除查询。
void batch(Connection connection,String sql,Object[] ...args) throws SQLException;

//返回结果集

}
