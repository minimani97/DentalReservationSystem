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

import com.sunmoon.reservation.model.*;

@Repository
public class DregisterDao {
	
	private static final Logger logger = LoggerFactory.getLogger(DregisterDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	//의사 등록(dentist_register.jsp)
	public void Dregister(String profile, String d_name, String d_tel, String email, String c_code, String d_field, String edu, String career) {
		logger.info("Dao///RequestMapping / Dregister");
		logger.info("Dao까지~:" + profile +"/"+ d_name+"/"+d_tel+"/"+email+"/"+c_code+"/"+d_field+"/"+edu+"/"+career);
		
		int c_codeNum=0, d_codeNum=0, d_fieldNum = 0;
		//c_code 값 가져오기
		String sqlStatement1 = "select c_code from clinic where c_name = '"+c_code+"';";
		c_codeNum= jdbcTemplate.queryForObject(sqlStatement1, int.class);
		//dentist 테이블에 컬럼값 넣기
		String sqlStatement ="insert into dentist (profile, d_name, d_tel, email, c_code, edu, career)"
				+ " VALUES ('"+profile+"','"+d_name+"', '"+d_tel+"', '"+email+"', '"+c_codeNum+"', '"+edu+"', '"+career+"')";
		jdbcTemplate.update(sqlStatement);

		//이름에 맞는 의료진 코드값 가져오기
		String sqlStatement3 = "select d_code from dentist where d_name = '"+d_name+"';";
		d_codeNum= jdbcTemplate.queryForObject(sqlStatement3, int.class);
		//진료과에 맞는 진료과 코드 가져오기
		String sqlStatement4 = "select f_code from field_info where f_name = '"+d_field+"';";
		d_fieldNum= jdbcTemplate.queryForObject(sqlStatement4, int.class);
		//dentist_field table에 컬럼 값 넣기
		String sqlStatement5 = "insert into dentist_field (c_code, d_code, d_field) VALUES ('"+c_codeNum+"', '"+d_codeNum+"', '"+d_fieldNum+"');";
		jdbcTemplate.update(sqlStatement5);
	}
	
	//의료진 리스트 가져오기
	public List<Dentist> DList(int c_code){
		logger.info("Dao - RequestMapping /DList.");
		
		String sqlStatement = "select d_code, profile, d_name, d_tel, email from dentist where c_code='" + c_code +"'";
		
		return jdbcTemplate.query(sqlStatement, new RowMapper<Dentist>() {

			@Override
			public Dentist mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Dentist dentist = new Dentist();
				dentist.setD_code(Integer.parseInt(rs.getString("d_code")));
				dentist.setProfile(rs.getString("profile"));
				dentist.setD_name(rs.getString("d_name"));
				dentist.setD_tel(rs.getString("d_tel"));
				dentist.setEmail(rs.getString("email"));

				return dentist;
				
			}
			
		});
	}
	
	//의료진 진료과 리스트 가져오기
		public List<D_field_List> d_field_List(int c_code, int d_code){
			logger.info("Dao - RequestMapping /d_field_List.");
			
			String sqlStatement = "select d_code, f_name, d_field, c_code " + 
					"from field_info, dentist_field " + 
					"where d_field = f_code and d_code = "+d_code+" and c_code = "+c_code+";";
			
			return jdbcTemplate.query(sqlStatement, new RowMapper<D_field_List>() {

				@Override
				public D_field_List mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					D_field_List d_field_list = new D_field_List();
					d_field_list.setD_code(Integer.parseInt(rs.getString("d_code")));
					d_field_list.setF_name(rs.getString("f_name"));
					d_field_list.setD_field(Integer.parseInt(rs.getString("d_field")));
					d_field_list.setC_code(Integer.parseInt(rs.getString("c_code")));
					
					return d_field_list;
					
				}
				
			});
		}
		
	//당일날짜 환자 받기(patient_list.jps)
	public List<Today_PList> today_PList(int c_code, int d_code){
		logger.info("Dao - RequestMapping /Today_PList.");
					
		String sqlStatement = "select distinct id, r.p_code, r.p_name, birth, p_tel\r\n" + 
				"from reservation r \r\n" + 
				"	inner join field_info f\r\n" + 
				"		on r_field = f_code\r\n" + 
				"	inner join patient p\r\n" + 
				"		on r.p_name = p.p_name\r\n" + 
				"where r.c_code="+c_code+" and r.d_code="+d_code+" ;";
		/*
		 * String sqlStatement =
		 * "select id, r.p_code, r.p_name, birth, p_tel, date, r_field, f_name, r.d_code, d_name, extra_info, personal_info "
		 * + "from reservation r " + "	inner join field_info f " +
		 * "		on r_field = f_code " + "	inner join dentist d " +
		 * "		on r.d_code = d.d_code " + "	inner join patient p " +
		 * "		on r.p_name = p.p_name " +
		 * "where r.c_code="+c_code+" and r.d_code="+d_code+" and r.date = curdate();";
		 */
					
		return jdbcTemplate.query(sqlStatement, new RowMapper<Today_PList>() {

			@Override
			public Today_PList mapRow(ResultSet rs, int rowNum) throws SQLException {
							
				Today_PList today_PList = new Today_PList();
					
				today_PList.setId(rs.getString("id"));
				today_PList.setP_code(Integer.parseInt(rs.getString("p_code")));
				today_PList.setP_name(rs.getString("p_name"));
				today_PList.setBirth(rs.getString("birth"));
				today_PList.setP_tel(rs.getString("p_tel"));

				return today_PList;			
			}
				
		});
	}
	
	//환자 한명의 진료 기록 리스트 가져오기(patient_record.jps)
	public List<Patient_record> one_Patient_List(int p_code, int c_code){
		logger.info("Dao - RequestMapping /One_Patient_List.");
						
		String sqlStatement = "select r.num, r.p_code, r.p_name, date, birth, p_tel, f_name, d_name, extra_info, personal_info, content " + 
				"from reservation r " + 
				"	inner join patient p " + 
				"		on r.p_name = p.p_name " + 
				"	inner join field_info fi " + 
				"		on r_field = f_code " + 
				"	inner join dentist d " + 
				"		on r.d_code = d.d_code " + 
				/*
				 * "   inner join medicine m " + "       on m.r_num = r.num " +
				 */
				"where r.c_code="+c_code+" and r.p_code = "+p_code+";";
						
		return jdbcTemplate.query(sqlStatement, new RowMapper<Patient_record>() {

			@Override
			public Patient_record mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Patient_record patient_record = new Patient_record();
				
				patient_record.setNum(Integer.parseInt(rs.getString("num")));
				patient_record.setP_code(Integer.parseInt(rs.getString("p_code")));
				patient_record.setP_name(rs.getString("p_name"));
				patient_record.setDate(rs.getString("date"));
				patient_record.setBirth(rs.getString("birth"));
				patient_record.setP_tel(rs.getString("p_tel"));
				patient_record.setF_name(rs.getString("f_name"));
				patient_record.setD_name(rs.getString("d_name"));
				patient_record.setExtra_info(rs.getString("extra_info"));
				patient_record.setPersonal_info(rs.getString("personal_info"));
				patient_record.setContent(rs.getString("content"));
				/*
				 * patient_record.setM_code(Integer.parseInt(rs.getString("m_code")));
				 * patient_record.setM_name(rs.getString("m_name"));
				 * patient_record.setDose(rs.getString("dose"));
				 * patient_record.setFrequency_day(Integer.parseInt(rs.getString("frequency_day"
				 * ))); patient_record.setDay(Integer.parseInt(rs.getString("day")))
				 */;
					
				return patient_record;		
			}
					
		});
	}
	//약처방 값 가져오기(patient_record.jsp)
	public List<Medicine> getPriscription(int num){
		logger.info("Dao - RequestMapping /getPriscription.");
						
		String sqlStatement = "select * from medicine where r_num="+num+";";
						
		return jdbcTemplate.query(sqlStatement, new RowMapper<Medicine>() {

			@Override
			public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Medicine medicine = new Medicine();
				
				medicine.setM_code(Integer.parseInt(rs.getString("m_code")));
				medicine.setM_name(rs.getString("m_name"));
				medicine.setDose(rs.getString("dose"));
				medicine.setFrequency_day(Integer.parseInt(rs.getString("frequency_day"))); 
				medicine.setDay(Integer.parseInt(rs.getString("day")));
					
				logger.info("약처방~~~`~: " + medicine);
				return medicine;		
			}
					
		});
	}
	
	//개인 약 추가(patient_record.jsp)
	public void addMedicine(int p_num, int m_code, String m_name, String dose,int frequency_day, int day ) {
		logger.info("Dao - RequestMapping /addMedicine");
			
		  String sqlStatement = "INSERT INTO medicine (m_code, m_name, dose, frequency_day, day, r_num) " +
		  "VALUES ("+m_code+", '"+m_name+"', '"+dose+"', "+frequency_day+", "+day+", "+p_num+");";
		  jdbcTemplate.update(sqlStatement);
		 
	}
	
	//개인 진료 content 수정(patient_record.jsp)
	public void AlterContent(int p_num,
			String getContent/* , int m_code, String m_name, String dose,int frequency_day, int day */) {
		logger.info("Dao - RequestMapping /AlterContent");
		
		String sqlStatement = "update reservation SET content = '"+getContent+"' WHERE num = "+ p_num;
		jdbcTemplate.update(sqlStatement);
		
		/*
		 * String sqlStatement2 =
		 * "INSERT INTO medicine (m_code, m_name, dose, frequency_day, day, r_num) " +
		 * "VALUES ("+m_code+", '"+m_name+"', '"+dose+"', "+frequency_day+", "+day+", "
		 * +p_num+");"; jdbcTemplate.update(sqlStatement2);
		 */
	}
	
	//DB에서 치과 select태그의 option 만들기(patient_transfer.jsp)
	public List<Clinic> makeOption(){
		logger.info("Dao - RequestMapping /makeOption.");
						
		String sqlStatement = "select c_name, c_code from clinic;";
						
		return jdbcTemplate.query(sqlStatement, new RowMapper<Clinic>() {

			@Override
			public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Clinic clinic = new Clinic();
				clinic.setC_name(rs.getString("c_name"));
				clinic.setC_code(Integer.parseInt(rs.getString("c_code").toString()));
				
				return clinic;
			}
					
		});
	}

	//선택한 병원과 진료과에 맞는 의료진 select-option 띄우기(patient_transfer.jsp)
	public List<Dentist> makeDentistOption(String selectClinic, String selectField){
		logger.info("Dao - RequestMapping /makeDentistOption.");
						
		String sqlStatement = "select d_name, d.d_code " + 
				"from dentist_field df " + 
				"	inner join dentist d " + 
				"		on df.d_code = d.d_code " + 
				"where df.c_code in ( " + 
				"	select c_code from clinic where c_name = '"+selectClinic+"') and " + 
				"      d_field in ( " + 
				"	select f_code from field_info where f_name = '"+selectField+"');";
						
		return jdbcTemplate.query(sqlStatement, new RowMapper<Dentist>() {

			@Override
			public Dentist mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Dentist dentist = new Dentist();
				dentist.setD_name(rs.getString("d_name"));
				dentist.setD_code(Integer.parseInt(rs.getString("d_code").toString()));
				
				logger.info("의료진 목록: "+ dentist);
				
				return dentist;
			}
					
		});
	}
	
	//트랜스퍼할 환자 select 태그의 option 만들기(patient_transfer.jsp)
	public List<Today_PList> makePatientOption(int c_code){
		logger.info("Dao - RequestMapping /makePatientOption.");
		
		String sqlStatement = "select distinct id, p.p_code, p.p_name, birth, p_tel " + 
				"from patient p " + 
				"	inner join reservation r " + 
				"		on r.p_code = p.p_code " + 
				"where c_code = "+c_code+";";
							
		/*
		 * String sqlStatement =
		 * "SELECT r.p_code, r.p_name, birth, p_tel, f_name, extra_info, content " +
		 * "FROM reservation r " + "	inner join patient p " +
		 * "		on r.p_code = p.p_code " + "	inner join field_info fi " +
		 * "		on r_field = f_code " +
		 * "where c_code = "+c_code+" and date = curdate() and personal_info = 'Y';";
		 */
							
		return jdbcTemplate.query(sqlStatement, new RowMapper<Today_PList>() {

			@Override
			public Today_PList mapRow(ResultSet rs, int rowNum) throws SQLException {
									
				Today_PList transfer = new Today_PList();
				
				transfer.setId(rs.getString("id"));
				transfer.setP_code(Integer.parseInt(rs.getString("p_code")));
				transfer.setP_name(rs.getString("p_name"));
				transfer.setBirth(rs.getString("birth"));
				transfer.setP_tel(rs.getString("p_tel"));
				
//				transfer.setP_code(Integer.parseInt(rs.getString("p_code")));
//				transfer.setP_name(rs.getString("p_name"));
//				transfer.setBirth(rs.getString("birth"));
//				transfer.setP_tel(rs.getString("p_tel"));
//				transfer.setF_name(rs.getString("f_name"));
//				transfer.setExtra_info(rs.getString("extra_info"));
//				transfer.setContent(rs.getString("content"));
					
				logger.info("트랜스퍼 환자 목록: "+ transfer);
					
				return transfer;
			}
						
		});
	}
	
	//선택한 환자에 맞는 info가져오기_readonly(patient_record.jsp)
	public List<Today_PList> getPatientInformation(int c_code, String p_name, int p_code){
		logger.info("Dao - RequestMapping /getPatientInformation.");
							
		String sqlStatement = "select p.p_code, p.p_name, birth, p_tel\r\n" + 
				"from patient p\r\n" + 
				"where p_code="+p_code+";";
		/*
		 * String sqlStatement =
		 * "select r.p_name, birth, p_tel, f_name, extra_info, content " +
		 * "FROM reservation r " + "	inner join patient p " +
		 * "		on r.p_code = p.p_code " + "	inner join field_info fi " +
		 * "		on r_field = f_code " + "where c_code = "+c_code+" and r.p_name = '"
		 * +p_name+"' and date = curdate() and personal_info = 'Y';";
		 */
							
		return jdbcTemplate.query(sqlStatement, new RowMapper<Today_PList>() {

			@Override
			public Today_PList mapRow(ResultSet rs, int rowNum) throws SQLException {
									
				Today_PList transfer = new Today_PList();
				
				transfer.setP_name(rs.getString("p_name"));
				transfer.setBirth(rs.getString("birth"));
				transfer.setP_tel(rs.getString("p_tel"));
				
				/*
				 * transfer.setP_name(rs.getString("p_name"));
				 * transfer.setBirth(rs.getString("birth"));
				 * transfer.setP_tel(rs.getString("p_tel"));
				 * transfer.setF_name(rs.getString("f_name"));
				 * transfer.setExtra_info(rs.getString("extra_info"));
				 * transfer.setContent(rs.getString("content"));
				 */
					
				logger.info("ㅎㅇ: "+ transfer);
					
				return transfer;
			}
						
		});
	}
	
	//환자 트랜스퍼 전송 버튼(patient_record.jsp)
	public void transferSendButton (int sendClinic, int sendDentist, String receiveClinic, String transfer_r_field, String receiveDentist, int p_code, String selectedStartTime, String selectedEndTime, int ReservationSelect, String selectedDate) {
		logger.info("Dao - RequestMapping /transferSendButton");
			
		String sqlStatement1 = "INSERT INTO transfer (sendClinic, sendDentist, receiveClinic, receiveDentist, p_code) "
				+ "VALUES ("+sendClinic+", "+sendDentist+", '"+receiveClinic+"', '"+receiveDentist+"', "+p_code+");";
		jdbcTemplate.update(sqlStatement1);
		
		String sqlStatement2 = "update reservation " + 
				"set r_field = (select f_code from field_info where f_name = '"+transfer_r_field+"'), " + 
				"d_code = (select d_code from dentist where d_name = '"+receiveDentist+"', " + 
				"c_code = (select c_code from clinic where c_name = '"+receiveClinic+"', " + 
				"s_time = '"+selectedStartTime+"', e_time = '"+selectedEndTime+"', date = '"+selectedDate+"'  " + 
				"where (num = "+ReservationSelect+");";
		jdbcTemplate.update(sqlStatement2);
	}
	
	//받은 트랜스퍼
	public List<Patient> receiveTransfer(int c_code, int d_code){
		logger.info("Dao - RequestMapping /receiveTransfer.");
					
		String sqlStatement = "select distinct p.p_name, p.p_code, birth, p_tel " + 
				"from patient p " + 
				"	inner join transfer t " + 
				"		on t.p_code = p.p_code " + 
				"where receiveClinic = (select c_name from clinic where c_code="+c_code+") " + 
				" and receiveDentist = (select d_name from dentist where d_code="+d_code+");";
					
		return jdbcTemplate.query(sqlStatement, new RowMapper<Patient>() {

			@Override
			public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
							
				Patient patient = new Patient();
				patient.setP_name(rs.getString("p_name"));
				patient.setP_code(Integer.parseInt(rs.getString("p_code")));
				patient.setBirth(rs.getString("birth"));
				patient.setP_tel(rs.getString("p_tel"));
				
				logger.info("디에이오???? : " + rs.getString("p_name") + ", " + rs.getString("p_code"));
				
				return patient;			
			}
				
		});
	}
	//트랜스퍼할 진료 select 태그의 option 만들기(patient_transfer.jsp)
	public List<Today_PList> makeReservationOption(int c_code, int d_code, int selected_Patient){
		logger.info("Dao - RequestMapping /makeReservationOption.");
		
		  String sqlStatement = "SELECT num, r.p_name, birth, p_tel, f_name, extra_info, content, date " + 
		  		"FROM reservation r " + 
		  		"	inner join patient p " + 
		  		"		on r.p_code = p.p_code " + 
		  		"	inner join field_info fi " + 
		  		"		on r_field = f_code " + 
		  		"where c_code = "+c_code+" and d_code = "+d_code+" and "
		  				+ "date > date_add(now(), interval -30 day) and personal_info = 'Y' and r.p_code = "+selected_Patient+";";
		 
							
		return jdbcTemplate.query(sqlStatement, new RowMapper<Today_PList>() {

			@Override
			public Today_PList mapRow(ResultSet rs, int rowNum) throws SQLException {
									
				Today_PList transfer = new Today_PList();
				
				transfer.setNum(Integer.parseInt(rs.getString("num")));
				transfer.setP_name(rs.getString("p_name"));
				transfer.setBirth(rs.getString("birth"));
				transfer.setP_tel(rs.getString("p_tel"));
				transfer.setF_name(rs.getString("f_name"));
				transfer.setExtra_info(rs.getString("extra_info"));
				transfer.setContent(rs.getString("content"));
				transfer.setDate(rs.getString("date"));
					
				logger.info(" 진료 목록: "+ transfer);
					
				return transfer;
			}
						
		});
	}
	
	//선택한 진료에 맞는 info가져오기_readonly(patient_timeAlter.jsp)
	public List<Today_PList> getReservationInfo(int c_code, int num){
		logger.info("Dao - RequestMapping /getReservationInfo.");

			  String sqlStatement = "select r.p_code, r.p_name, birth, p.p_tel, f_name, extra_info, content " + 
			  		"from reservation r " + 
			  		"	inner join patient p " + 
			  		"		on r.p_code = p.p_code " + 
			  		"	inner join field_info fi " + 
			  		"		on r_field = f_code " + 
			  		"where num="+num+";";
			 
			  logger.info("진료ㅛㅛㅛㅛ: " + c_code + ", "+num);
								
		return jdbcTemplate.query(sqlStatement, new RowMapper<Today_PList>() {

			@Override
			public Today_PList mapRow(ResultSet rs, int rowNum) throws SQLException {
										
				Today_PList alter = new Today_PList();
					
				alter.setP_code(Integer.parseInt(rs.getString("p_code")));
				alter.setP_name(rs.getString("p_name"));
				alter.setBirth(rs.getString("birth"));
				alter.setP_tel(rs.getString("p_tel"));
				alter.setF_name(rs.getString("f_name"));
				alter.setExtra_info(rs.getString("extra_info"));
				alter.setContent(rs.getString("content"));
						
				logger.info("alterReservation: "+ alter);
						
				return alter;
			}
							
		});
	}
	
	//휴진 등록 버튼(close_notice.jsp)
	public void closeButton(String closeDate, String start_time, String end_time, String reason, int d_code) {
		logger.info("Dao - RequestMapping /closeButton");
		
		String sqlStatement = "insert into close_notice (d_code, closeDate, start_time, end_time, reason) "
				+ "values ("+d_code+", '"+closeDate+"', '"+start_time+"', '"+end_time+"', '"+reason+"');";
		jdbcTemplate.update(sqlStatement);
		
	}
}
