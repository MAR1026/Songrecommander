package com.teomc.mariah;

import javax.swing.JFrame;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class MainFrame extends JFrame {
	int cnt = 0; // 제네릭을 위한 카운트 변수
	public MainFrame() {
		
		DBBean DB = new DBBean(); // 데이터 베이스
		
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // 화면 크기 받아오기
		
		JFrame JFM = new JFrame("볼륨을 높여요"); // 메인 프레임
		JFM.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TEOMC\\Documents\\\uC219\uC81C\\Java\\SongRecommander\\\uD53D\uD1A0\uADF8\uB7A8\\569500-emotion-face\\256\\music-player.png"));
		JFM.getContentPane().setBackground(new Color(255, 255, 255));
		JFM.getContentPane().setLayout(null);
		JFM.setSize(res.width, res.height);
		JFM.setResizable(false); // 화면크기 강제고정
		JFM.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JFM.setDefaultCloseOperation(3);
		
		URL mariah_u = PlaySongFrame.class.getClassLoader().getResource("princessmimi_256.png");
		ImageIcon mariah = new ImageIcon(mariah_u);
		JLabel MARIAH = new JLabel(mariah);
		//미미겅듀 라벨(사진)
		MARIAH.setBounds(67, 69, 256, 256);
		JFM.getContentPane().add(MARIAH);
		
		URL songrc_u = PlaySongFrame.class.getClassLoader().getResource("music-player2.png");
		ImageIcon songrc = new ImageIcon(songrc_u);
		JLabel SongRc = new JLabel(songrc);
		//노래추천 라벨(픽토그램)
		SongRc.setBounds(67, 591, 256, 256);
		JFM.getContentPane().add(SongRc);
		
		URL happy_u = PlaySongFrame.class.getClassLoader().getResource("084-happy-4.png");
		ImageIcon happy = new ImageIcon(happy_u);
		JLabel Happy = new JLabel(happy);
	 // 즐거운 기분 라벨(이모티콘)
		Happy.setBounds(444, 69, 256, 256);
		JFM.getContentPane().add(Happy);
		
		URL gloomy_u = PlaySongFrame.class.getClassLoader().getResource("032-sad-7.png");
		ImageIcon gloomy = new ImageIcon(gloomy_u);
		JLabel Gloomy = new JLabel(gloomy);
	// 우울한 기분 라벨(이모티콘)
		Gloomy.setBounds(808, 69, 256, 256);
		JFM.getContentPane().add(Gloomy);
		
		URL annoyance_u = PlaySongFrame.class.getClassLoader().getResource("083-angry.png");
		ImageIcon annoyance = new ImageIcon(annoyance_u);
		JLabel Annoyance = new JLabel(annoyance);
	// 짜증난 기분 라벨(이모티콘)
		Annoyance.setBounds(1169, 69, 256, 256);
		JFM.getContentPane().add(Annoyance);
		
		URL depression_u = PlaySongFrame.class.getClassLoader().getResource("024-crying-1.png");
		ImageIcon depression = new ImageIcon(depression_u);
		JLabel Depression = new JLabel(depression);
	// 억울한 기분 라벨(이모티콘)
		Depression.setBounds(1529, 69, 256, 256);
		JFM.getContentPane().add(Depression);
		
		URL angry_u = PlaySongFrame.class.getClassLoader().getResource("076-angry-4.png");
		ImageIcon angry = new ImageIcon(angry_u);
		JLabel Angry = new JLabel(angry);
		//화난 기분 라벨(이모티콘)
		Angry.setBounds(444, 577, 256, 256);
		JFM.getContentPane().add(Angry);
		
		URL soso_u = PlaySongFrame.class.getClassLoader().getResource("077-sceptic.png");
		ImageIcon soso = new ImageIcon(soso_u);
		JLabel So_so = new JLabel(soso);
		//보통 기분 라벨(이모티콘)
		So_so.setBounds(808, 577, 256, 256);
		JFM.getContentPane().add(So_so);
		
		URL shock_u = PlaySongFrame.class.getClassLoader().getResource("093-shocked-1.png");
		ImageIcon shock = new ImageIcon(shock_u);
		JLabel Shock = new JLabel(shock);
		//놀란 기분 라벨(이모티콘)
		Shock.setBounds(1169, 577, 256, 256);
		JFM.getContentPane().add(Shock);
		
		URL sad_u = PlaySongFrame.class.getClassLoader().getResource("032-sad-7.png");
		ImageIcon sad = new ImageIcon(sad_u);
		JLabel Sad = new JLabel(sad);
		//슬픈 기분 라벨(이모티콘)
		Sad.setBounds(1529, 577, 256, 256);
		JFM.getContentPane().add(Sad);
		
		JButton MARIAH_Btn = new JButton("\uBBF8\uBBF8\uAC85\uB4C0");
		//미미겅듀 버튼(노래 재생 프레임으로 이동)
		MARIAH_Btn.setForeground(Color.WHITE);
		MARIAH_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		MARIAH_Btn.setFocusPainted(false);
		MARIAH_Btn.setBackground(Color.BLACK);
		MARIAH_Btn.setBounds(46, 351, 300, 150);
		JFM.getContentPane().add(MARIAH_Btn);

		JButton Happy_Btn = new JButton("\uC990\uAC70\uC6CC!"); // 즐거운 기분 버튼
		//즐거운 버튼(노래 재생 프레임으로 이동)
		Happy_Btn.setForeground(Color.WHITE);
		Happy_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Happy_Btn.setFocusPainted(false); // 클릭 시 나타나는 네모 줄 없애기
		Happy_Btn.setBackground(Color.BLACK);
		Happy_Btn.setBounds(422, 351, 300, 150);
		JFM.getContentPane().add(Happy_Btn);

		
		JButton Gloomy_Btn = new JButton("\uC6B0\uC6B8\uD574!"); // 
		//우울한 버튼(노래 재생 프레임으로 이동)
		Gloomy_Btn.setForeground(Color.WHITE);
		Gloomy_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Gloomy_Btn.setFocusPainted(false);
		Gloomy_Btn.setBackground(Color.BLACK);
		Gloomy_Btn.setBounds(786, 351, 300, 150);
		JFM.getContentPane().add(Gloomy_Btn);

		
		JButton Annoyance_Btn = new JButton("\uC9DC\uC99D\uB098!");
		//짜증난 버튼(노래 재생 프레임으로 이동)
		Annoyance_Btn.setForeground(Color.WHITE);
		Annoyance_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Annoyance_Btn.setFocusPainted(false);
		Annoyance_Btn.setBackground(Color.BLACK);
		Annoyance_Btn.setBounds(1149, 351, 300, 150);
		JFM.getContentPane().add(Annoyance_Btn);

		
		JButton Depression_Btn = new JButton("\uC5B5\uC6B8\uD574!");
		//억울한 버튼(노래 재생 프레임으로 이동)
		Depression_Btn.setForeground(Color.WHITE);
		Depression_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Depression_Btn.setFocusPainted(false);
		Depression_Btn.setBackground(Color.BLACK);
		Depression_Btn.setBounds(1509, 351, 300, 150);
		JFM.getContentPane().add(Depression_Btn);

		
		JButton Angry_Btn = new JButton("\uD654\uB098!");
		//화난 버튼(노래 재생 프레임으로 이동)
		Angry_Btn.setForeground(Color.WHITE);
		Angry_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Angry_Btn.setFocusPainted(false);
		Angry_Btn.setBackground(Color.BLACK);
		Angry_Btn.setBounds(422, 855, 300, 150);
		JFM.getContentPane().add(Angry_Btn);

		
		
		JButton Soso_Btn = new JButton("\uBCF4\uD1B5!");
		//보통 버튼(노래 재생 프레임으로 이동)
		Soso_Btn.setForeground(Color.WHITE);
		Soso_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Soso_Btn.setFocusPainted(false);
		Soso_Btn.setBackground(Color.BLACK);
		Soso_Btn.setBounds(786, 855, 300, 150);
		JFM.getContentPane().add(Soso_Btn);

		
		
		JButton Shock_Btn = new JButton("\uB180\uB77C\uC6CC!");
		//충격 버튼(노래 재생 프레임으로 이동)
		Shock_Btn.setForeground(Color.WHITE);
		Shock_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Shock_Btn.setFocusPainted(false);
		Shock_Btn.setBackground(Color.BLACK);
		Shock_Btn.setBounds(1149, 855, 300, 150);
		JFM.getContentPane().add(Shock_Btn);
		
		
		JButton Sad_Btn = new JButton("\uC2AC\uD37C!");
		//슬픈 버튼(노래 재생 프레임으로 이동)
		Sad_Btn.setForeground(Color.WHITE);
		Sad_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Sad_Btn.setFocusPainted(false);
		Sad_Btn.setBackground(Color.BLACK);
		Sad_Btn.setBounds(1509, 855, 300, 150);
		JFM.getContentPane().add(Sad_Btn);
		
		
		JButton SongRC_Btn = new JButton("노래 추천"); // 노래 추천하는 버튼
		//노래 추천 버튼(노래 추천 프레임으로 이동)
		SongRC_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		SongRC_Btn.setBackground(Color.black); // 버튼 배경색
		SongRC_Btn.setForeground(Color.white); // 버튼 글자색
		SongRC_Btn.setFocusPainted(false); // 클릭 시 나타나는 네모 줄 없애기
		SongRC_Btn.setBounds(46, 855, 300, 150);
		JFM.getContentPane().add(SongRC_Btn);
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == MARIAH_Btn){
				new PlaySongFrame("Mariah"); // 프레임 소환
				JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Happy_Btn){
					new PlaySongFrame("Happy"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Annoyance_Btn){
					new PlaySongFrame("Annoyance"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Gloomy_Btn){
					new PlaySongFrame("Gloomy"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Depression_Btn){
					new PlaySongFrame("Depression"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Angry_Btn){
					new PlaySongFrame("Angry"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Sad_Btn){
					new PlaySongFrame("Sad"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Shock_Btn){
					new PlaySongFrame("Shock"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == Soso_Btn){
					new PlaySongFrame("Soso"); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
				else if(e.getSource() == SongRC_Btn){
					new SongRecommandFrame(); // 프레임 소환
					JFM.dispose(); // 프레임 종료 
				}
			}
		};
		
		MARIAH_Btn.addActionListener(listener);
		Happy_Btn.addActionListener(listener);
		Annoyance_Btn.addActionListener(listener);
		Gloomy_Btn.addActionListener(listener);
		Depression_Btn.addActionListener(listener);
		Angry_Btn.addActionListener(listener);
		Sad_Btn.addActionListener(listener);
		Shock_Btn.addActionListener(listener);
		Soso_Btn.addActionListener(listener);
		SongRC_Btn.addActionListener(listener);
		
		JFM.setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
