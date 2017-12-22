package com.teomc.mariah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DBBean { // DB 연결, 입력, 선택, 삭제를 담당하는 클래스
	public Connection Connect_DB() { // DB 연결하는 메소드
		Connection conn = null; // 연결 도구
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://10.80.162.48:3306/Song", "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// DB에 입력하는 메소드
	public int Insert_DB(String name, String Singer, String Emotion, String MUSIC_FILE, String Img_File) throws SQLException {
		Connection conn = null; // 연결 도구
		PreparedStatement pstmt = null; // 쿼리문
		ResultSet rs = null; // 쿼리문 실행

		try {
			conn = Connect_DB(); // DB 연결
			pstmt = conn.prepareStatement("insert into song (SongName, SInger, Emotion, SongFile, ImgFile, comment_DIV, comment, comment_cnt) values (?, ?, ?, ?, ?, '0', '0', '0')"); // SQL문
			pstmt.setString(1, name); // SongName
			pstmt.setString(2, Singer); // Singer
			pstmt.setString(3, Emotion); // Emotion
			pstmt.setString(4, MUSIC_FILE); // SongFile
			pstmt.setString(5, Img_File); // ImgFile

			rs = pstmt.executeQuery(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace(); // 오류 내역 출력
			return 0; // 입력 실패시 0 반환
		} finally {
			if (pstmt != null)
				try {
					pstmt.close(); 
				} catch (Exception e2) {
					// TODO: handle exception
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
		return 1; // 입력 성공 시 1 반환
	}

	// DB에서 노래 가져오는 메소드, 선택한 감정에 따라 변동, 제네릭 사용
	public ArrayList<DataBean> select_DB(String Emotion) { 
		ArrayList<DataBean> result = new ArrayList<DataBean>(); // 제네릭
		String sql = "select * from song where Emotion=?"; // 입력할 쿼리문
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // 쿼리문
		ResultSet rs = null; // 결과 & 실행
		ResultSet rs_OnlyRand = null; // 랜덤 값을 위한 결과 & 실행

		try {
			conn = Connect_DB(); // DB 접속

			if (Emotion != null) {
				pstmt = conn.prepareStatement(sql); // SQL 문 입력
				pstmt.setString(1, Emotion); // 검색 조건(Emotion) 설정
			}
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			rs_OnlyRand = pstmt.executeQuery(); // 랜덤 값을 위한 쿼리문 실행

			int RandNum, Max = -999, i, Flag = 0; // RandNum : 가져올 노래의 SongId, MAX : 랜덤 함수의 최대값, Flag : 반복문 종료를 위한 것 
			int[] NumArr = new int[100];
			for (i = 0; i < 100; i++) {
				NumArr[i] = 0; // 배열 초기화
			}

			i = 0;

			while (rs_OnlyRand.next() == true) {
				NumArr[i++] = rs_OnlyRand.getInt("SongId"); // 배열에 DB에 있는 노래의 ID 저장
				if (rs_OnlyRand.getInt("SongId") > Max) // 가장 높은 노래의 ID 저장
					Max = rs_OnlyRand.getInt("SongId");
		
			}
			Random random = new Random(); 
			while (true) {
				RandNum = random.nextInt(Max) + 1; // 난수 생성
				for (i = 0; NumArr[i] != 0; i++) { 
					if (NumArr[i] == RandNum) { // 난수와 일치하는 노래의 ID가 있는지 검사
						Flag = 1; // 반복문 종료를 위한 플래그 설정
						break;
					}
				}
				if (Flag == 1)
					break;
			}

			while (rs.next() == true) {
				if (rs.getInt("SongId") == RandNum) { // 가져올 노래의 SongId와 일치한 지 검사
					DataBean bean = new DataBean();
					bean.setSongId(rs.getInt("SongId"));
					bean.setSongName(rs.getString("SongName"));
					bean.setSinger(rs.getString("Singer"));
					bean.setEmotion(rs.getString("Emotion"));
					bean.setSongFile(rs.getString("SongFile"));
					bean.setImgFile(rs.getString("ImgFile"));
					result.add(bean); // 제네릭에 추가
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
		}
		return result; // 노래 정보 반환
	}
	
	public int InsertComment_DB(int Comment, int SongId) throws SQLException{ // 평가를 저장하기 위한 Insert 문 -> 관리자가 평점 확인 후 낮으면 곡 삭제
		
		Connection conn = null; // 연결 도구
		PreparedStatement pstmt = null; // 쿼리문
		ResultSet rs = null; // 쿼리문 실행
		String sql = "select comment, comment_cnt from song Where SongId = ?"; // 기존 DB에 저장되어 있는 평점 총 합과 평가 횟수 가져오기
		int Comment_Cnt = 0;
		int comment_DB = 0;
		
		try {
		
			conn = Connect_DB(); // DB 연결
			pstmt = conn.prepareStatement(sql); // SQL문
			pstmt.setInt(1, SongId); // SongID
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			
		if(rs.next() == true){
				comment_DB = rs.getInt("comment"); // DB에 저장되어 있던 평점 종 합 가져오기
				Comment_Cnt = rs.getInt("comment_cnt"); // DB에 저장되어 있던 평가 횟수 가져오기
			}		
			pstmt = conn.prepareStatement("update song set comment_DIV = ?, comment = ?, comment_cnt = ? where SongId = ?");
			pstmt.setDouble(1, ((comment_DB + Comment) / ++Comment_Cnt)); // 평점 더한 뒤 평가 횟수로 나누기
			pstmt.setInt(2, Comment + comment_DB); // 평점 총합 저장하기
			pstmt.setInt(3, Comment_Cnt); // 평가 횟수 저장하기
			pstmt.setInt(4, SongId);
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			
		} catch (Exception e) {
			e.printStackTrace(); // 오류 내역 출력
			return 0; // 입력 실패시 0 반환
		} finally {
			if (pstmt != null)
				try {
					pstmt.close(); 
				} catch (Exception e2) {
					// TODO: handle exception
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
		return 1; // 입력 성공 시 1 반환
	}
}
