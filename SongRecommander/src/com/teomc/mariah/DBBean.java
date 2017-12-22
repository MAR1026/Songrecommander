package com.teomc.mariah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DBBean { // DB ����, �Է�, ����, ������ ����ϴ� Ŭ����
	public Connection Connect_DB() { // DB �����ϴ� �޼ҵ�
		Connection conn = null; // ���� ����
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://10.80.162.48:3306/Song", "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// DB�� �Է��ϴ� �޼ҵ�
	public int Insert_DB(String name, String Singer, String Emotion, String MUSIC_FILE, String Img_File) throws SQLException {
		Connection conn = null; // ���� ����
		PreparedStatement pstmt = null; // ������
		ResultSet rs = null; // ������ ����

		try {
			conn = Connect_DB(); // DB ����
			pstmt = conn.prepareStatement("insert into song (SongName, SInger, Emotion, SongFile, ImgFile, comment_DIV, comment, comment_cnt) values (?, ?, ?, ?, ?, '0', '0', '0')"); // SQL��
			pstmt.setString(1, name); // SongName
			pstmt.setString(2, Singer); // Singer
			pstmt.setString(3, Emotion); // Emotion
			pstmt.setString(4, MUSIC_FILE); // SongFile
			pstmt.setString(5, Img_File); // ImgFile

			rs = pstmt.executeQuery(); // ������ ����
		} catch (Exception e) {
			e.printStackTrace(); // ���� ���� ���
			return 0; // �Է� ���н� 0 ��ȯ
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
		return 1; // �Է� ���� �� 1 ��ȯ
	}

	// DB���� �뷡 �������� �޼ҵ�, ������ ������ ���� ����, ���׸� ���
	public ArrayList<DataBean> select_DB(String Emotion) { 
		ArrayList<DataBean> result = new ArrayList<DataBean>(); // ���׸�
		String sql = "select * from song where Emotion=?"; // �Է��� ������
		Connection conn = null; // ����
		PreparedStatement pstmt = null; // ������
		ResultSet rs = null; // ��� & ����
		ResultSet rs_OnlyRand = null; // ���� ���� ���� ��� & ����

		try {
			conn = Connect_DB(); // DB ����

			if (Emotion != null) {
				pstmt = conn.prepareStatement(sql); // SQL �� �Է�
				pstmt.setString(1, Emotion); // �˻� ����(Emotion) ����
			}
			
			rs = pstmt.executeQuery(); // ������ ����
			rs_OnlyRand = pstmt.executeQuery(); // ���� ���� ���� ������ ����

			int RandNum, Max = -999, i, Flag = 0; // RandNum : ������ �뷡�� SongId, MAX : ���� �Լ��� �ִ밪, Flag : �ݺ��� ���Ḧ ���� �� 
			int[] NumArr = new int[100];
			for (i = 0; i < 100; i++) {
				NumArr[i] = 0; // �迭 �ʱ�ȭ
			}

			i = 0;

			while (rs_OnlyRand.next() == true) {
				NumArr[i++] = rs_OnlyRand.getInt("SongId"); // �迭�� DB�� �ִ� �뷡�� ID ����
				if (rs_OnlyRand.getInt("SongId") > Max) // ���� ���� �뷡�� ID ����
					Max = rs_OnlyRand.getInt("SongId");
		
			}
			Random random = new Random(); 
			while (true) {
				RandNum = random.nextInt(Max) + 1; // ���� ����
				for (i = 0; NumArr[i] != 0; i++) { 
					if (NumArr[i] == RandNum) { // ������ ��ġ�ϴ� �뷡�� ID�� �ִ��� �˻�
						Flag = 1; // �ݺ��� ���Ḧ ���� �÷��� ����
						break;
					}
				}
				if (Flag == 1)
					break;
			}

			while (rs.next() == true) {
				if (rs.getInt("SongId") == RandNum) { // ������ �뷡�� SongId�� ��ġ�� �� �˻�
					DataBean bean = new DataBean();
					bean.setSongId(rs.getInt("SongId"));
					bean.setSongName(rs.getString("SongName"));
					bean.setSinger(rs.getString("Singer"));
					bean.setEmotion(rs.getString("Emotion"));
					bean.setSongFile(rs.getString("SongFile"));
					bean.setImgFile(rs.getString("ImgFile"));
					result.add(bean); // ���׸��� �߰�
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
		return result; // �뷡 ���� ��ȯ
	}
	
	public int InsertComment_DB(int Comment, int SongId) throws SQLException{ // �򰡸� �����ϱ� ���� Insert �� -> �����ڰ� ���� Ȯ�� �� ������ �� ����
		
		Connection conn = null; // ���� ����
		PreparedStatement pstmt = null; // ������
		ResultSet rs = null; // ������ ����
		String sql = "select comment, comment_cnt from song Where SongId = ?"; // ���� DB�� ����Ǿ� �ִ� ���� �� �հ� �� Ƚ�� ��������
		int Comment_Cnt = 0;
		int comment_DB = 0;
		
		try {
		
			conn = Connect_DB(); // DB ����
			pstmt = conn.prepareStatement(sql); // SQL��
			pstmt.setInt(1, SongId); // SongID
			
			rs = pstmt.executeQuery(); // ������ ����
			
		if(rs.next() == true){
				comment_DB = rs.getInt("comment"); // DB�� ����Ǿ� �ִ� ���� �� �� ��������
				Comment_Cnt = rs.getInt("comment_cnt"); // DB�� ����Ǿ� �ִ� �� Ƚ�� ��������
			}		
			pstmt = conn.prepareStatement("update song set comment_DIV = ?, comment = ?, comment_cnt = ? where SongId = ?");
			pstmt.setDouble(1, ((comment_DB + Comment) / ++Comment_Cnt)); // ���� ���� �� �� Ƚ���� ������
			pstmt.setInt(2, Comment + comment_DB); // ���� ���� �����ϱ�
			pstmt.setInt(3, Comment_Cnt); // �� Ƚ�� �����ϱ�
			pstmt.setInt(4, SongId);
			
			rs = pstmt.executeQuery(); // ������ ����
			
		} catch (Exception e) {
			e.printStackTrace(); // ���� ���� ���
			return 0; // �Է� ���н� 0 ��ȯ
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
		return 1; // �Է� ���� �� 1 ��ȯ
	}
}
