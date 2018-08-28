package my.service;

import java.util.List;

import my.dao.Dao;
import my.domain.City;
import my.domain.Province;

public class Service {
	Dao dao = new Dao();
	
	//查询出所有省份
	public List<Province> findAllProvince() {
		return dao.findAllProvince();
	}
	
	//根据省份的pid，查询出省份下所有的城市
	public List<City> findByPid(int pid) {
		return dao.findByPid(pid);
	}
}
