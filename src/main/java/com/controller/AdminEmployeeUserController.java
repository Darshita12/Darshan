package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AdminEmployeeUserBean;
import com.dao.AdminEmployeeUserDao;

@RestController
@RequestMapping(value="/user")
public class AdminEmployeeUserController {
			/*
		 * @RequestMapping(value="/addProduct",method=RequestMethod.POST) public
		 * ProductBean addProduct() { return null; }
		 */
		@Autowired
		AdminEmployeeUserDao adminEmployeeUserDao;
		
		@PostMapping(value="/addUser")
		public AdminEmployeeUserBean addUser(AdminEmployeeUserBean adminEmployeeUserBean)
		{
			int res=adminEmployeeUserDao.addUser(adminEmployeeUserBean);
			System.out.println(res);
			return adminEmployeeUserBean;
		}
		@GetMapping(value="/displayUser")
		public List<AdminEmployeeUserBean> displayUser()
		{
			List<AdminEmployeeUserBean> list=new ArrayList<AdminEmployeeUserBean>();
			list=adminEmployeeUserDao.displayDetails();
			return list;
		}
		@DeleteMapping(value="/deleteUser/{id}")
		public ResponseEntity deleteUser(@PathVariable int id)
		{
			int res=adminEmployeeUserDao.deleteUser(id);
			if(res>0)
			{
				return new ResponseEntity("deleted",HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
		}
		@PostMapping(value="/updateUser/{id}")
		public ResponseEntity updateProduct(AdminEmployeeUserBean adminEmployeeUserBean,@PathVariable int id)
		{
			adminEmployeeUserBean.setId(id);
			int res=adminEmployeeUserDao.updateUser(adminEmployeeUserBean);
			if(res>0)
			{
				return new ResponseEntity("updated",HttpStatus.ACCEPTED);
			}
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}
		@PatchMapping(value="/getDataById/{id}")
		public List<AdminEmployeeUserBean> getDataById(@PathVariable int id)
		{
			List<AdminEmployeeUserBean> list=new ArrayList<AdminEmployeeUserBean>();
			list=adminEmployeeUserDao.getDataById(id);
			return list;
			
		}
		
	}


