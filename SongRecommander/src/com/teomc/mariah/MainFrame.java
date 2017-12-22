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
	int cnt = 0; // ���׸��� ���� ī��Ʈ ����
	public MainFrame() {
		
		DBBean DB = new DBBean(); // ������ ���̽�
		
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // ȭ�� ũ�� �޾ƿ���
		
		JFrame JFM = new JFrame("������ ������"); // ���� ������
		JFM.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TEOMC\\Documents\\\uC219\uC81C\\Java\\SongRecommander\\\uD53D\uD1A0\uADF8\uB7A8\\569500-emotion-face\\256\\music-player.png"));
		JFM.getContentPane().setBackground(new Color(255, 255, 255));
		JFM.getContentPane().setLayout(null);
		JFM.setSize(res.width, res.height);
		JFM.setResizable(false); // ȭ��ũ�� ��������
		JFM.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JFM.setDefaultCloseOperation(3);
		
		URL mariah_u = PlaySongFrame.class.getClassLoader().getResource("princessmimi_256.png");
		ImageIcon mariah = new ImageIcon(mariah_u);
		JLabel MARIAH = new JLabel(mariah);
		//�̹̰ϵ� ��(����)
		MARIAH.setBounds(67, 69, 256, 256);
		JFM.getContentPane().add(MARIAH);
		
		URL songrc_u = PlaySongFrame.class.getClassLoader().getResource("music-player2.png");
		ImageIcon songrc = new ImageIcon(songrc_u);
		JLabel SongRc = new JLabel(songrc);
		//�뷡��õ ��(����׷�)
		SongRc.setBounds(67, 591, 256, 256);
		JFM.getContentPane().add(SongRc);
		
		URL happy_u = PlaySongFrame.class.getClassLoader().getResource("084-happy-4.png");
		ImageIcon happy = new ImageIcon(happy_u);
		JLabel Happy = new JLabel(happy);
	 // ��ſ� ��� ��(�̸�Ƽ��)
		Happy.setBounds(444, 69, 256, 256);
		JFM.getContentPane().add(Happy);
		
		URL gloomy_u = PlaySongFrame.class.getClassLoader().getResource("032-sad-7.png");
		ImageIcon gloomy = new ImageIcon(gloomy_u);
		JLabel Gloomy = new JLabel(gloomy);
	// ����� ��� ��(�̸�Ƽ��)
		Gloomy.setBounds(808, 69, 256, 256);
		JFM.getContentPane().add(Gloomy);
		
		URL annoyance_u = PlaySongFrame.class.getClassLoader().getResource("083-angry.png");
		ImageIcon annoyance = new ImageIcon(annoyance_u);
		JLabel Annoyance = new JLabel(annoyance);
	// ¥���� ��� ��(�̸�Ƽ��)
		Annoyance.setBounds(1169, 69, 256, 256);
		JFM.getContentPane().add(Annoyance);
		
		URL depression_u = PlaySongFrame.class.getClassLoader().getResource("024-crying-1.png");
		ImageIcon depression = new ImageIcon(depression_u);
		JLabel Depression = new JLabel(depression);
	// ����� ��� ��(�̸�Ƽ��)
		Depression.setBounds(1529, 69, 256, 256);
		JFM.getContentPane().add(Depression);
		
		URL angry_u = PlaySongFrame.class.getClassLoader().getResource("076-angry-4.png");
		ImageIcon angry = new ImageIcon(angry_u);
		JLabel Angry = new JLabel(angry);
		//ȭ�� ��� ��(�̸�Ƽ��)
		Angry.setBounds(444, 577, 256, 256);
		JFM.getContentPane().add(Angry);
		
		URL soso_u = PlaySongFrame.class.getClassLoader().getResource("077-sceptic.png");
		ImageIcon soso = new ImageIcon(soso_u);
		JLabel So_so = new JLabel(soso);
		//���� ��� ��(�̸�Ƽ��)
		So_so.setBounds(808, 577, 256, 256);
		JFM.getContentPane().add(So_so);
		
		URL shock_u = PlaySongFrame.class.getClassLoader().getResource("093-shocked-1.png");
		ImageIcon shock = new ImageIcon(shock_u);
		JLabel Shock = new JLabel(shock);
		//��� ��� ��(�̸�Ƽ��)
		Shock.setBounds(1169, 577, 256, 256);
		JFM.getContentPane().add(Shock);
		
		URL sad_u = PlaySongFrame.class.getClassLoader().getResource("032-sad-7.png");
		ImageIcon sad = new ImageIcon(sad_u);
		JLabel Sad = new JLabel(sad);
		//���� ��� ��(�̸�Ƽ��)
		Sad.setBounds(1529, 577, 256, 256);
		JFM.getContentPane().add(Sad);
		
		JButton MARIAH_Btn = new JButton("\uBBF8\uBBF8\uAC85\uB4C0");
		//�̹̰ϵ� ��ư(�뷡 ��� ���������� �̵�)
		MARIAH_Btn.setForeground(Color.WHITE);
		MARIAH_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		MARIAH_Btn.setFocusPainted(false);
		MARIAH_Btn.setBackground(Color.BLACK);
		MARIAH_Btn.setBounds(46, 351, 300, 150);
		JFM.getContentPane().add(MARIAH_Btn);

		JButton Happy_Btn = new JButton("\uC990\uAC70\uC6CC!"); // ��ſ� ��� ��ư
		//��ſ� ��ư(�뷡 ��� ���������� �̵�)
		Happy_Btn.setForeground(Color.WHITE);
		Happy_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Happy_Btn.setFocusPainted(false); // Ŭ�� �� ��Ÿ���� �׸� �� ���ֱ�
		Happy_Btn.setBackground(Color.BLACK);
		Happy_Btn.setBounds(422, 351, 300, 150);
		JFM.getContentPane().add(Happy_Btn);

		
		JButton Gloomy_Btn = new JButton("\uC6B0\uC6B8\uD574!"); // 
		//����� ��ư(�뷡 ��� ���������� �̵�)
		Gloomy_Btn.setForeground(Color.WHITE);
		Gloomy_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Gloomy_Btn.setFocusPainted(false);
		Gloomy_Btn.setBackground(Color.BLACK);
		Gloomy_Btn.setBounds(786, 351, 300, 150);
		JFM.getContentPane().add(Gloomy_Btn);

		
		JButton Annoyance_Btn = new JButton("\uC9DC\uC99D\uB098!");
		//¥���� ��ư(�뷡 ��� ���������� �̵�)
		Annoyance_Btn.setForeground(Color.WHITE);
		Annoyance_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Annoyance_Btn.setFocusPainted(false);
		Annoyance_Btn.setBackground(Color.BLACK);
		Annoyance_Btn.setBounds(1149, 351, 300, 150);
		JFM.getContentPane().add(Annoyance_Btn);

		
		JButton Depression_Btn = new JButton("\uC5B5\uC6B8\uD574!");
		//����� ��ư(�뷡 ��� ���������� �̵�)
		Depression_Btn.setForeground(Color.WHITE);
		Depression_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Depression_Btn.setFocusPainted(false);
		Depression_Btn.setBackground(Color.BLACK);
		Depression_Btn.setBounds(1509, 351, 300, 150);
		JFM.getContentPane().add(Depression_Btn);

		
		JButton Angry_Btn = new JButton("\uD654\uB098!");
		//ȭ�� ��ư(�뷡 ��� ���������� �̵�)
		Angry_Btn.setForeground(Color.WHITE);
		Angry_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Angry_Btn.setFocusPainted(false);
		Angry_Btn.setBackground(Color.BLACK);
		Angry_Btn.setBounds(422, 855, 300, 150);
		JFM.getContentPane().add(Angry_Btn);

		
		
		JButton Soso_Btn = new JButton("\uBCF4\uD1B5!");
		//���� ��ư(�뷡 ��� ���������� �̵�)
		Soso_Btn.setForeground(Color.WHITE);
		Soso_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Soso_Btn.setFocusPainted(false);
		Soso_Btn.setBackground(Color.BLACK);
		Soso_Btn.setBounds(786, 855, 300, 150);
		JFM.getContentPane().add(Soso_Btn);

		
		
		JButton Shock_Btn = new JButton("\uB180\uB77C\uC6CC!");
		//��� ��ư(�뷡 ��� ���������� �̵�)
		Shock_Btn.setForeground(Color.WHITE);
		Shock_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Shock_Btn.setFocusPainted(false);
		Shock_Btn.setBackground(Color.BLACK);
		Shock_Btn.setBounds(1149, 855, 300, 150);
		JFM.getContentPane().add(Shock_Btn);
		
		
		JButton Sad_Btn = new JButton("\uC2AC\uD37C!");
		//���� ��ư(�뷡 ��� ���������� �̵�)
		Sad_Btn.setForeground(Color.WHITE);
		Sad_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Sad_Btn.setFocusPainted(false);
		Sad_Btn.setBackground(Color.BLACK);
		Sad_Btn.setBounds(1509, 855, 300, 150);
		JFM.getContentPane().add(Sad_Btn);
		
		
		JButton SongRC_Btn = new JButton("�뷡 ��õ"); // �뷡 ��õ�ϴ� ��ư
		//�뷡 ��õ ��ư(�뷡 ��õ ���������� �̵�)
		SongRC_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		SongRC_Btn.setBackground(Color.black); // ��ư ����
		SongRC_Btn.setForeground(Color.white); // ��ư ���ڻ�
		SongRC_Btn.setFocusPainted(false); // Ŭ�� �� ��Ÿ���� �׸� �� ���ֱ�
		SongRC_Btn.setBounds(46, 855, 300, 150);
		JFM.getContentPane().add(SongRC_Btn);
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == MARIAH_Btn){
				new PlaySongFrame("Mariah"); // ������ ��ȯ
				JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Happy_Btn){
					new PlaySongFrame("Happy"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Annoyance_Btn){
					new PlaySongFrame("Annoyance"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Gloomy_Btn){
					new PlaySongFrame("Gloomy"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Depression_Btn){
					new PlaySongFrame("Depression"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Angry_Btn){
					new PlaySongFrame("Angry"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Sad_Btn){
					new PlaySongFrame("Sad"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Shock_Btn){
					new PlaySongFrame("Shock"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == Soso_Btn){
					new PlaySongFrame("Soso"); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
				}
				else if(e.getSource() == SongRC_Btn){
					new SongRecommandFrame(); // ������ ��ȯ
					JFM.dispose(); // ������ ���� 
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
