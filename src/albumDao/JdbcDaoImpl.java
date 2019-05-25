package albumDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import albumUtils.ReflectionUtils;

/*
 * 使用QueryRunner提供具体实现
 * */
public class JdbcDaoImpl<T> implements DAO_1<T> {
	private QueryRunner queryRunner = null;
	private Class<T> type;

	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type=ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void update(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}

	@Override
	public T get(Connection connection, String sql, Object... args) throws SQLException {
		return  queryRunner.query(connection, sql,new BeanHandler<>(type),args);
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
	 return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
	}

	@Override
	public void batch(Connection connection, String sql, Object[]... args) throws SQLException {
		queryRunner.batch(connection, sql, args);
	}

}
