package com.sunmoon.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.UserInfo;

@Repository
public class LoginDao {

	private static final Logger logger = LoggerFactory.getLogger(LoginDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void saveUserInfo(UserInfo info) {
		String sqlStatement = "insert into login (id, password, kindof) values (?,?,?)";
		jdbcTemplate.update(sqlStatement, info.getId(), info.getPassword(), info.getGroup());
		
		String sqlStatement2 = "insert into patient (id, p_name, birth, addr, p_tel) values (?,?,?,?,?)";
		jdbcTemplate.update(sqlStatement2, info.getId(), info.getP_name(), info.getBirth(), info.getAddr(), info.getP_tel());
	}
	
	public List<UserInfo> goLogin(UserInfo info) {
		String sqlStatement = "select count(*) as correct, kindof, id from login where id='"+info.getId()+"' and password='"+info.getPassword()+"' and kindof='"+info.getGroup()+"'";
		logger.info("SQLSTATEMENT : " + sqlStatement);
		
		List<UserInfo> result = jdbcTemplate.query(sqlStatement, new RowMapper<UserInfo>() {
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo userInfo = new UserInfo();
				
				userInfo.setFlg(rs.getInt("correct"));
				userInfo.setGroup(rs.getString("kindof"));
				userInfo.setId(rs.getString("id"));
				
				logger.info("??????????????????????correct: " + rs.getString("kindof"));
				
				
				return userInfo;
			}
		});
		return result;
	}
	
	public UserInfo sessionP(String id) {

		//logger.info(id);

		String sqlStatement = "select * from patient where id='"+id+"' and isLeader='Y'";
		logger.info("여기다!!!!!!!!!!!!!!!!!!!:" + sqlStatement);

		try {
			logger.info("UserInfo checkLogin try.");

			return jdbcTemplate.queryForObject(sqlStatement, new RowMapper<UserInfo>() {
				@Override
				public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					logger.info("jdbc다음다음다음다음이제 여기왔냐구우우ㅜㅜㅜㅇ우우웅ㅇㅇㅇㅇㅇ!!");
					logger.info("아이디값 찍어본다 시보랄 : " + rs.getString("id") );
					UserInfo userInfo = new UserInfo();
					userInfo.setId(rs.getString("id"));
					//userInfo.setPassword(rs.getString("password"));
					userInfo.setP_code(rs.getInt("p_code"));
					userInfo.setP_name(rs.getString("p_name"));
					userInfo.setBirth(rs.getString("birth"));
					userInfo.setAddr(rs.getString("addr"));
					userInfo.setP_tel(rs.getString("p_tel"));

					logger.info("rs에서 값을 받앗을까욥????????");
					logger.info("rs.getSstringID : " + userInfo.getId());
					logger.info("*******userinfo select Value*******: " + userInfo.toString());
					// request.getSession().setAttribute("signedUser",
					// userInfo);
					return userInfo;
				}

			});

		} catch (Exception e) {
			logger.info("DB Exception");
			return new UserInfo();
		}

	}
	
	
	
	public DentistInfo sessionD(String id) {

		logger.info(id);

		String sqlStatement = "select * from dentist where id='"+id+"'";
		logger.info("여기다!!!!!!!!!!!!!!!!!!!:" + sqlStatement);

		try {
			logger.info("UserInfo checkLogin try.");

			return jdbcTemplate.queryForObject(sqlStatement, new RowMapper<DentistInfo>() {
				@Override
				public DentistInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					logger.info("jdbc다음다음다음다음이제 여기왔냐구우우ㅜㅜㅜㅇ우우웅ㅇㅇㅇㅇㅇ!!");
					logger.info("아이디값 찍어본다 시보랄 : " + rs.getString("id") );
					DentistInfo dentistInfo = new DentistInfo();
					dentistInfo.setId(rs.getString("id"));
					//userInfo.setPassword(rs.getString("password"));
					dentistInfo.setD_code(rs.getInt("d_code"));
					dentistInfo.setC_code(rs.getInt("c_code"));
					dentistInfo.setProfile(rs.getString("profile"));
					dentistInfo.setD_name(rs.getString("d_name"));
					dentistInfo.setD_tel(rs.getString("d_tel"));
					dentistInfo.setEmail(rs.getString("email"));
					dentistInfo.setEdu(rs.getString("edu"));
					dentistInfo.setCareer(rs.getString("career"));
					
					logger.info("rs에서 값을 받앗을까욥????????");
					logger.info("rs.getSstringID : " + dentistInfo.getId());
					logger.info("*******userinfo select Value*******: " + dentistInfo.toString());
					// request.getSession().setAttribute("signedUser",
					// userInfo);
					return dentistInfo;
				}

			});

		} catch (Exception e) {
			logger.info("DB Exception");
			return new DentistInfo();
		}

	}
	
	

}
