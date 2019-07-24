package com.sunmoon.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunmoon.reservation.model.ReservationInfo;

@Repository
public class PushAlarmDao {
	private static final Logger logger = LoggerFactory.getLogger(ClinicInfoDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	
	//���� ��¥���� ���� ��¥ ���� ȯ�������� ��������
	public List<ReservationInfo> pushInfoDao() {
		
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, 1);
		
		String day = date.format(c.getTime());
		logger.info("��¥ ��Ʈ�� : "+day);
		
		String sql = "select r.p_name, date, s_time, c_name, token "+
					"from reservation r inner join clinic c on r.c_code = c.c_code"+
				" inner join patient p on r.p_code = p.p_code where date ='"+day+"';";
		logger.info(sql);
		
		
		return jdbcTemplate.query(sql, new RowMapper<ReservationInfo>() {

			@Override
			public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReservationInfo info = new ReservationInfo();
				
				
				info.setP_name(rs.getString("p_name"));
				info.setDate(rs.getString("date"));
				info.setS_time(rs.getString("s_time"));
				info.setC_name(rs.getString("c_name"));
				
				
				logger.info("����� ȯ�� �̸� :"+rs.getString("p_name"));

				return info;
			}

		});
	}
	
	//�ش� �Ƿ��� ������ ������ ��� ȯ�� ���� ��������
	public List<ReservationInfo> allPushInfoDao() {
		String sql = "select token from patient;"; //�ش� �Ƿ��� ���� ���� �߰��� �߰��� ��
		logger.info(sql);
	
		return jdbcTemplate.query(sql, new RowMapper<ReservationInfo>() {

			@Override
			public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReservationInfo info = new ReservationInfo();
				
				info.setToken(rs.getString("token"));
							
				logger.info("��ū :"+rs.getString("token"));

				return info;
			}

		});
	}
	
	
	
}
