package my.dao;

import java.sql.SQLException;
import java.util.List;

import my.domain.City;
import my.domain.Province;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class Dao {
	//简化jdbc操作的类
	QueryRunner qr = new TxQueryRunner();

	//查询所有省
	public List<Province> findAllProvince() {
		String sql = "SELECT * FROM t_province";
		
		try {
			//查询出来的Province中，它的CityList不会被赋值，因为在数据库表中，没有这个数据
			List<Province> provinceList = qr.query(sql, new BeanListHandler<Province>(Province.class));
			return provinceList;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 通过提供的省pid
	 * 查询省下所有的市
	 * */
	public List<City> findByPid(int pid) {
		String sql = "SELECT * FROM t_city WHERE pid=?";
		try {
			List<City> cityList = qr.query(sql, new BeanListHandler<City>(City.class), pid);
			return cityList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
