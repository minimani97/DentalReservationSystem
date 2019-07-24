package com.sunmoon.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.ReservationInfo;
import com.sunmoon.reservation.model.ReservationList;

@Repository
public class MypageDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	// 아이디에 등록된 가족 불러오기
	public List<PatientInfo> loadFamilyInfo(int user_code) {
		// 해당 회원의 id값과 가족 등록 여부 플래그 정보 가져오기
				String sqlStatement = "select family from patient where patient.p_code = " + user_code;
				String family_flag = jdbcTemplate.queryForObject(sqlStatement, String.class);
				
				if(family_flag == "N") {  // 등록한 가족이 없는 경우
					String sqlStatement1 = "select p_code, p_name from patient where patient.p_code"+user_code;
					
					List<PatientInfo> result = jdbcTemplate.query(sqlStatement1, new RowMapper<PatientInfo>() {
						
						@Override
						public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
							PatientInfo info = new PatientInfo();
							
							info.setName(rs.getString("p_name"));
							info.setP_code(rs.getInt("p_code"));
						
							return info;
						}
					});
					
					return result;
				}
				else {   // 등록한 가족이 있는 경우
					String sqlStatement2 = "select p_code, p_name from patient where id in (select id from patient where p_code="+user_code+")";
					
					List<PatientInfo> result = jdbcTemplate.query(sqlStatement2, new RowMapper<PatientInfo>() {
						
						@Override
						public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
							PatientInfo info = new PatientInfo();
							
							info.setName(rs.getString("p_name"));
							info.setP_code(rs.getInt("p_code"));
							
							return info;
						}
					});
					
					return result;
				}
	}
	
	// 비밀번호 변경하기
	public String changePassword(int p_code, String password) {
		String sqlStatement = "update login set login.password=? where id in (select id from patient where patient.p_code=?)";
		jdbcTemplate.update(sqlStatement, password, p_code);
		
		return "Success";
	}
	
	// 내 정보 수정하기
	 public PatientInfo editMyInfo(PatientInfo info) { 
		 String sqlStatement = "update patient set patient.addr=?, patient.p_tel=? where patient.p_code=?";
		 jdbcTemplate.update(sqlStatement, info.getAddr(), info.getTel(), info.getP_code());
		 
		 return info;
	 }
	 
	 // 가족 정보 불러오기
	 public List<PatientInfo> loadFamilyList(int p_code) {
		 String sqlStatement = "select m_name, m_birth, m_tel, relationship from family_info where id in (select id from patient where p_code="+p_code+")";
		 List<PatientInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<PatientInfo>() {
			
			 @Override
			 public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				 PatientInfo info = new PatientInfo();
				 
				 info.setName(rs.getString("m_name"));
				 info.setBirth(rs.getString("m_birth"));
				 info.setRelationship(rs.getString("relationship"));
				 
				 String tel = rs.getString("m_tel");
				 info.setTel(tel);
				 
				 String query = "select p_code from patient where patient.p_tel='"+tel+"'";
				 int p_code = jdbcTemplate.queryForObject(query, Integer.class);
				 info.setP_code(p_code);
				 
				 return info;
			 }
			 
		 });
		 
		 return results;
	 }
	 
	 // 가족 등록하기
	 public void registerFamilyInfo(PatientInfo info) {
		
		 // 가족 등록 여부 플래그 정보 가져오기
		 String sqlStatement = "select family from patient where p_code="+info.getP_code();
		 String result = jdbcTemplate.queryForObject(sqlStatement, String.class);
		 
		 if(result == "N") {  // 등록된 가족이 한 명도 없는 경우
			 
			 // 가족 등록 여부 플래그 Y로 바꾸기 
			 String changeFlgQuery = "update patient set family='Y' where patient.p_code="+info.getP_code();
			 jdbcTemplate.update(changeFlgQuery);
			 
		 } 
		 
		// 해당 id값 가져오기
		 String getIdQuery = "select id from patient where patient.p_code="+info.getP_code();
		 String id = jdbcTemplate.queryForObject(getIdQuery, String.class);
		 
		 // 등록하려는 가족 정보 DB에 저장하기
		 String sqlStatement1 = "insert into patient (id, p_name, birth, family, addr, p_tel) values (?,?,?,?,?,?)";
		 jdbcTemplate.update(sqlStatement1, id, info.getName(), info.getBirth(), "Y", info.getAddr(), info.getTel());
		 
		 String sqlStatement2 = "insert into family_info (id, m_name, m_birth, m_addr, m_tel, relationship) values (?,?,?,?,?,?)";
		 jdbcTemplate.update(sqlStatement2, id, info.getName(), info.getBirth(), info.getAddr(), info.getTel(), info.getRelationship());
		
	 }
	 
	 // 가족 정보 삭제하기
	 public void deleteFamilyInfo(int p_code) {
		 
		 String sqlStatement1 = "select count(*) from family_info where id in (select id from patient where patient.p_code="+p_code+")";
		 int cnt_family = jdbcTemplate.queryForObject(sqlStatement1, Integer.class);
		 
		 if(cnt_family == 2) {
			 String getUserCodeQuery = "select p_code from patient where p_tel in (select m_tel from family_info where id in (select id from patient where p_code="+p_code+") and relationship='본인')";
			 int user_code = jdbcTemplate.queryForObject(getUserCodeQuery, Integer.class);
			 
			 String changeFamilyFlg = "update patient set family='N' where p_code="+user_code;
			 jdbcTemplate.update(changeFamilyFlg);
		 }
		 
		 String sqlStatement2 = "delete from family_info where m_tel in (select p_tel from patient where patient.p_code="+p_code+")";
		 jdbcTemplate.update(sqlStatement2);
		 
		 String sqlStatement3 = "delete from patient where patient.p_code="+p_code;
		 jdbcTemplate.update(sqlStatement3);
		
	 }
	 
	 // 예약 리스트 불러오기
	 public List<ReservationList> loadReservationList(int p_code) {
		 
		 String sqlStatement = "select reservation.num, reservation.p_code, reservation.date, reservation.s_time, reservation.e_time, clinic.c_name, field_info.f_name "
		 		+ "from field_info, reservation, clinic where reservation.c_code = clinic.c_code and reservation.r_field=field_info.f_code and p_code="+p_code;
		 List<ReservationList> results = jdbcTemplate.query(sqlStatement, new RowMapper<ReservationList>() {
			 
			 @Override
			 public ReservationList mapRow(ResultSet rs, int rowNum) throws SQLException {
				 ReservationList info = new ReservationList();
				 
				 info.setNum(rs.getInt("num"));
				 info.setP_code(rs.getInt("p_code"));
				 info.setDate(rs.getString("date"));
				 info.setS_time(rs.getString("s_time"));
				 info.setE_time(rs.getString("e_time"));
				 info.setC_name(rs.getString("c_name"));
				 info.setF_name(rs.getString("f_name"));
				 
				 return info;
			 }
			 
		 });
		 
		 return results;
	 }
	 
	 // 해당 예약 정보 불러오기
	 public List<ReservationInfo> getReservationInformation(int num) {
		 
		 String sqlStatement = "select reservation.num, reservation.p_code, reservation.p_name, reservation.date, " + 
		 		"reservation.s_time, reservation.e_time, field_info.f_name, clinic.c_name, " + 
		 		"dentist.d_name, reservation.extra_info, reservation.personal_info " + 
		 		"from reservation, field_info, clinic, dentist " + 
		 		"where reservation.r_field=field_info.f_code and clinic.c_code=reservation.c_code " + 
		 		"and dentist.d_code=reservation.d_code and reservation.num=" + num;
		 List<ReservationInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<ReservationInfo>() {
			
			 @Override 
			 public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				 ReservationInfo info = new ReservationInfo();
				 
				 info.setNum(rs.getInt("num"));
				 info.setP_code(rs.getInt("p_code"));
				 info.setP_name(rs.getString("p_name"));
				 info.setDate(rs.getString("date"));
				 info.setS_time(rs.getString("s_time"));
				 info.setE_time(rs.getString("e_time"));
				 info.setF_name(rs.getString("f_name"));
				 info.setC_name(rs.getString("c_name"));
				 info.setD_name(rs.getString("d_name"));
				 info.setExtra_info(rs.getString("extra_info"));
				 info.setPersonal_info(rs.getString("personal_info"));
				 
				 return info;
			 }
		 });
		 
		 return results;
	 }
	 
	 // 해당 예약 취소하기
	 public void cancelReservation(int num) {
		 String sqlStatement = "delete from reservation where reservation.num="+num;
		 jdbcTemplate.update(sqlStatement);
	 }
	 
	// 환자 홈 화면의 유저 이름 띄우기를 위한 이름 값 가져오기
	public String loadUserName(int user_code) {
		String sqlStatement = "select p_name from patient where p_code=" + user_code;
		String result = jdbcTemplate.queryForObject(sqlStatement, String.class);
		
		return result;
	}
}
