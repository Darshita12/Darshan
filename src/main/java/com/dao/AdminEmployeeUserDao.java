package com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.AdminEmployeeUserBean;



//spring bean
@Repository
public class AdminEmployeeUserDao {

		@Autowired
		JdbcTemplate jdbcTemplate;
		public int addUser(AdminEmployeeUserBean adminEmployeeUserBean)
		{
			return jdbcTemplate.update("insert into adminemployeeuser(name,email,contactnumber,password,nationality,address,type)values('"+adminEmployeeUserBean.getName()+"','"+adminEmployeeUserBean.getEmail()+"','"+adminEmployeeUserBean.getContactnumber()+"','"+adminEmployeeUserBean.getPassword()+"','"+adminEmployeeUserBean.getNationality()+"','"+adminEmployeeUserBean.getAddress()+"','"+adminEmployeeUserBean.getType()+"')");
		}
		private final static class UserMapper implements RowMapper<AdminEmployeeUserBean>
		{
			
			@Override
			public AdminEmployeeUserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminEmployeeUserBean adminEmployeeUserBean=new AdminEmployeeUserBean();
				adminEmployeeUserBean.setId(rs.getInt("id"));;
				adminEmployeeUserBean.setName(rs.getString("name"));
				adminEmployeeUserBean.setEmail(rs.getString("email"));
				adminEmployeeUserBean.setContactnumber(rs.getString("contactnumber"));
				adminEmployeeUserBean.setPassword(rs.getString("password"));
				adminEmployeeUserBean.setNationality(rs.getString("nationality"));
				adminEmployeeUserBean.setAddress(rs.getString("address"));
				adminEmployeeUserBean.setType(rs.getString("type"));
				
				return adminEmployeeUserBean;
				
			}
		}
		public List displayDetails()
		{
			return jdbcTemplate.query("select * from adminemployeeuser", new UserMapper());
		}
		public int deleteUser(int id)
		{
			return jdbcTemplate.update("delete from product where id="+id+"");
		}
		public int updateUser(AdminEmployeeUserBean adminEmployeeUserBean)
		{
			return jdbcTemplate.update("update product set name='"+adminEmployeeUserBean.getName()+"',email='"+adminEmployeeUserBean.getEmail()+"', contactnumber='"+adminEmployeeUserBean.getContactnumber()+"', password='"+adminEmployeeUserBean.getPassword()+"', nationality='"+adminEmployeeUserBean.getNationality()+"', address='"+adminEmployeeUserBean.getAddress()+"', type='"+adminEmployeeUserBean.getType()+"' where id="+adminEmployeeUserBean.getId()+"");
		}

		public List<AdminEmployeeUserBean> getDataById(int id)
		{
			return jdbcTemplate.query("select * from product where id="+id+"",new UserMapper());
		}
	}


