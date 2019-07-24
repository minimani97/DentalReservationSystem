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

import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.FieldInfo;
import com.sunmoon.reservation.model.GetCFDName;
import com.sunmoon.reservation.model.ReservationInfo;

@Repository
public class ReservationDao {
	private static final Logger logger = LoggerFactory.getLogger(ReservationDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public List<ClinicMapInfo> getMapInfo() {
		logger.info("DAO 접근 성공");

		String sqlClinicCode = "select c_code, c_name, addr_y, addr_x, c_tel from clinic where certify='Y';";

		return jdbcTemplate.query(sqlClinicCode, new RowMapper<ClinicMapInfo>() {
			@Override
			public ClinicMapInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClinicMapInfo mapInfo = new ClinicMapInfo();

				mapInfo.setC_code(rs.getString("c_code"));
				mapInfo.setC_name(rs.getString("c_name"));
				mapInfo.setC_y(rs.getString("addr_y"));
				mapInfo.setC_x(rs.getString("addr_x"));
				mapInfo.setC_tel(rs.getString("c_tel"));
				return mapInfo;
			}
		});
	}

	// 선택한 병원의 진료 과목 불러오기
	public List<FieldInfo> loadFieldInfo(String selectedClinic) {
		logger.info("Dao_loadFieldInfo Function");
		
		String sqlStatement = "select distinct f_name from dentist_field, field_info where dentist_field.d_field=field_info.f_code and dentist_field.c_code="+selectedClinic;
		
		List<FieldInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<FieldInfo>() {
			
			@Override
			public FieldInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				FieldInfo info = new FieldInfo();
				
				info.setFieldName(rs.getString("f_name"));
				
				return info;
			}
		});
		
		return results;
	}
	
	// 진료과목 코드 불러오기
	public int getFcode(String f_name) {
		logger.info("Dao_getFcode Function");
		
		String sqlStatement = "select f_code from field_info where field_info.f_name='"+f_name+"'";
		
		int loadedFieldCode = jdbcTemplate.queryForObject(sqlStatement, Integer.class);
		
		return loadedFieldCode;		
	}
	
	// 선택한 진료분야에 따른 의료진 정보 불러오기
	public List<DentistInfo> loadDentistInfo(String selectedClinic, String selectedField) {
		logger.info("Dao_loadDentistInfo Function");
		
		String sqlStatement = "select * from dentist_field, dentist where dentist.d_code=dentist_field.d_code and dentist.c_code="+selectedClinic+" and dentist_field.d_field="+selectedField;
		
		List<DentistInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<DentistInfo>() {
			
			@Override
			public DentistInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				DentistInfo info = new DentistInfo();
				
				info.setD_code(rs.getInt("d_code"));
				info.setD_name(rs.getString("d_name"));
				info.setD_tel(rs.getString("d_tel"));
				info.setEmail(rs.getString("email"));
				info.setProfile(rs.getString("profile"));
				
				return info;
			}
		});
		
		return results;
	}
	
	// 의료진 코드 불러오기
	public int getDcode(String d_name) {
		logger.info("Dao_getDcode Function");
		
		String sqlStatement = "select d_code from dentist where d_name='"+d_name+"'";
		
		int loadedDentistCode = jdbcTemplate.queryForObject(sqlStatement, Integer.class);
		
		return loadedDentistCode;
	}
	
	// 이미 예약된 시간 정보 불러오기
	public List<AlreadyReservedTimeInfo> getAlreadyReservedTimeInfo(String date, int d_code) {
		logger.info("Dao_getAlreadyReservedTimeInfo Function");
		
		String sqlStatement = "select s_time, e_time from reservation where reservation.date='"+date+"' and reservation.d_code="+d_code;
		
		List<AlreadyReservedTimeInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<AlreadyReservedTimeInfo>() {
			
			@Override
			public AlreadyReservedTimeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AlreadyReservedTimeInfo info = new AlreadyReservedTimeInfo();
				
				info.setStartTime(rs.getString("s_time"));
				info.setEndTime(rs.getString("e_time"));
				
				return info;
			}
		});
		
		return results;
	}
	
	// 치과/진료과목/의료진 코드로 치과명/진료과목명/의료진명 불러오기
	public List<GetCFDName> getNames(int c_code, int f_code, int d_code) {
		logger.info("Dao_GetCFDName Function");
		
		String sqlStatement = "select c_name, d_name, f_name from dentist, dentist_field, field_info, clinic "
				+ "where dentist.d_code = dentist_field.d_code and dentist_field.d_field = field_info.f_code "
				+ "and clinic.c_code = dentist.c_code and clinic.c_code = "+c_code+" and dentist.d_code = "+d_code+" "
				+ "and field_info.f_code = "+f_code;
		
		List<GetCFDName> result = jdbcTemplate.query(sqlStatement, new RowMapper<GetCFDName>() {
			
			@Override
			public GetCFDName mapRow(ResultSet rs, int rowNum) throws SQLException {
				GetCFDName info = new GetCFDName();
				
				info.setC_name(rs.getString("c_name"));
				info.setF_name(rs.getString("f_name"));
				info.setD_name(rs.getString("d_name"));
				
				return info;
			}
		});
		
		return result;
	}
	
	// 등록된 가족의 코드와 이름 가져오기
	public List<PatientInfo> getFamilyList(int user_code) {
		logger.info("Dao_getFamilyList Function");
		
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
	
	// 해당 환자 정보 불러오기
	public List<PatientInfo> getPatientInfo(int p_code) {
		logger.info("Dao_getPatientInfo Function");
		
		String sqlStatement = "select p_code, p_name, birth, addr, p_tel from patient where patient.p_code="+p_code;
		
		List<PatientInfo> result = jdbcTemplate.query(sqlStatement, new RowMapper<PatientInfo>() {
			
			@Override
			public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PatientInfo info = new PatientInfo();
				
				info.setName(rs.getString("p_name"));
				info.setP_code(rs.getInt("p_code"));
				info.setBirth(rs.getString("birth"));
				info.setAddr(rs.getString("addr"));
				info.setTel(rs.getString("p_tel"));
				
				return info;
			}
			
		});
		
		return result;
	}
	
	// 예약 정보 저장하기
	public void saveReservationInfo(ReservationInfo info) {
		logger.info("Dao_saveReservationInfo Function");
		
		String sqlStatement = "insert into reservation (p_code, p_name, date, s_time, e_time, r_field, c_code, d_code, extra_info, personal_info) values (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlStatement, info.getP_code(), info.getP_name(), info.getDate(), info.getS_time(), info.getE_time(), info.getR_field(), info.getC_code(), info.getD_code(), info.getExtra_info(), info.getPersonal_info());
	}
}
