package com.teomc.mariah;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class PlaySongFrame extends JFrame {
	
	DBBean DB = new DBBean();
	MusicPlay player = new MusicPlay();
	public PlaySongFrame(String Emotion) {

		ArrayList<DataBean> List = DB.select_DB(Emotion);
		String SongName = List.get(0).getSongName();
		String Singer = List.get(0).getSinger();
		String SongFile = List.get(0).getSongFile();
		String ImgFIle = List.get(0).getImgFile();
		int SongId = List.get(0).getSongId();
		
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // ȭ�� ũ��
																		// �޾ƿ���

		JFrame JFP = new JFrame("�뷡 ���");
		JFP.getContentPane().setLayout(null);
		JFP.getContentPane().setBackground(new Color(255, 255, 255));
		JFP.setSize(res.width, res.height);
		JFP.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JFP.setResizable(false);

		JLabel SongName_Label = new JLabel(SongName);
		SongName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		SongName_Label.setForeground(Color.BLACK);
		SongName_Label.setFont(new Font("�޸ո���T", Font.PLAIN, 55));
		SongName_Label.setBounds(902, 55, 1000, 200);
		JFP.getContentPane().add(SongName_Label);

		JLabel Singer_Label = new JLabel(Singer);
		Singer_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Singer_Label.setFont(new Font("�޸ո���T", Font.PLAIN, 50));
		Singer_Label.setBounds(1402, 178, 500, 200);
		JFP.getContentPane().add(Singer_Label);
		
		JLabel Img_Label = new JLabel();
		Img_Label.setIcon(new ImageIcon(ImgFIle));
		Img_Label.setBounds(12, 178, 800, 800);
		JFP.getContentPane().add(Img_Label);

		JButton Return_Btn = new JButton("\uB3CC\uC544\uAC00\uAE30");
		Return_Btn.setForeground(Color.WHITE);
		Return_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Return_Btn.setFocusPainted(false);
		Return_Btn.setBackground(Color.BLACK);
		Return_Btn.setBounds(12, 10, 300, 150);
		JFP.getContentPane().add(Return_Btn);

		JButton Comment_Btn = new JButton("\uD3C9\uC810\uB4F1\uB85D");
		Comment_Btn.setForeground(Color.WHITE);
		Comment_Btn.setFont(new Font("HY������B", Font.ITALIC, 60));
		Comment_Btn.setFocusPainted(false);
		Comment_Btn.setBackground(Color.BLACK);
		Comment_Btn.setBounds(1602, 876, 300, 150);
		JFP.getContentPane().add(Comment_Btn);

		URL play_u = PlaySongFrame.class.getClassLoader().getResource("play-button.png");
		URL play_r = PlaySongFrame.class.getClassLoader().getResource("play-button_roll.png");
		URL pause_u = PlaySongFrame.class.getClassLoader().getResource("pause.png");
		URL pause_r = PlaySongFrame.class.getClassLoader().getResource("pause_roll.png");
		
		ImageIcon Play = new ImageIcon(play_u);
		ImageIcon Play_roll = new ImageIcon(play_r);
		ImageIcon Play_press = new ImageIcon(pause_u);
		ImageIcon Pause_roll = new ImageIcon(pause_r);
		JButton Play_Btn = new JButton(Play);
		Play_Btn.setBounds(1111, 460, 256, 256);
		JFP.getContentPane().add(Play_Btn);
		Play_Btn.setBorderPainted(false);
		Play_Btn.setContentAreaFilled(false);
		Play_Btn.setFocusPainted(false);
		// Play_Btn.setPressedIcon(Play_press); // ��ư�� Ŭ������ �� ǥ���� �̹���
		Play_Btn.setRolloverIcon(Play_roll); // ��ư�� ���콺�� ������ ���� �� ǥ���� �̹���

		URL Stop_u = PlaySongFrame.class.getClassLoader().getResource("stop.png");
		URL Stop_r = PlaySongFrame.class.getClassLoader().getResource("stop_roll.png");
		ImageIcon Stop = new ImageIcon(Stop_u);
		ImageIcon Stop_roll = new ImageIcon(Stop_r);
		JButton Stop_Btn = new JButton(Stop);
		Stop_Btn.setFocusPainted(false);
		Stop_Btn.setContentAreaFilled(false);
		Stop_Btn.setBorderPainted(false);
		Stop_Btn.setBounds(1379, 460, 256, 256);
		JFP.getContentPane().add(Stop_Btn);
		Stop_Btn.setRolloverIcon(Stop_roll);

		URL before_u = PlaySongFrame.class.getClassLoader().getResource("rewind.png");
		URL before_r = PlaySongFrame.class.getClassLoader().getResource("rewind_roll.png");
		ImageIcon Before = new ImageIcon(before_u);
		ImageIcon Before_roll = new ImageIcon(before_r);
		JButton Before_Btn = new JButton(Before);
		Before_Btn.setFocusPainted(false);
		Before_Btn.setContentAreaFilled(false);
		Before_Btn.setBorderPainted(false);
		Before_Btn.setBounds(843, 460, 256, 256);
		JFP.getContentPane().add(Before_Btn);
		Before_Btn.setRolloverIcon(Before_roll);

		URL Next_u = PlaySongFrame.class.getClassLoader().getResource("fast-forward.png");
		URL Next_r = PlaySongFrame.class.getClassLoader().getResource("fast-forward_roll.png");
		ImageIcon Next = new ImageIcon(Next_u);
		ImageIcon Next_roll = new ImageIcon(Next_r);
		JButton Next_Btn = new JButton(Next);
		Next_Btn.setFocusPainted(false);
		Next_Btn.setContentAreaFilled(false);
		Next_Btn.setBorderPainted(false);
		Next_Btn.setBounds(1646, 460, 256, 256);
		JFP.getContentPane().add(Next_Btn);
		Next_Btn.setRolloverIcon(Next_roll);
		
		String[] Comment_List = { "��", "�ڡ�", "�ڡڡ�", "�ڡڡڡ�", "�ڡڡڡڡ�" };
		
		JComboBox Comment_combobox = new JComboBox(Comment_List);
		Comment_combobox.setFont(new Font("�޸ո���T", Font.PLAIN, 50));
		Comment_combobox.setBounds(1290, 876, 300, 150);
		JFP.getContentPane().add(Comment_combobox);

		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Return_Btn) {
					new MainFrame();
					player.stop(); // ���ư��� ��ư ������ �� ���� ����
					JFP.dispose();
				} 
				else if (e.getSource() == Comment_Btn) {
					String Comment_str = Comment_combobox.getSelectedItem().toString();
					int Comment = 0;
					if (Comment_str == "��") {
						Comment = 1;
					}
					else if(Comment_str == "�ڡ�"){
						Comment = 2;
					}
					else if(Comment_str == "�ڡڡ�"){
						Comment = 3;
					}
					else if(Comment_str == "�ڡڡڡ�"){
						Comment = 4;
					}
					else if(Comment_str == "�ڡڡڡڡ�"){
						Comment = 5;
					}
					try {
						DB.InsertComment_DB(Comment, SongId); // ������ DB�� ����
						JOptionPane.showMessageDialog(null, "���� ��Ͽ� �����Ͽ����ϴ�.");
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "���� ��Ͽ� �����Ͽ����ϴ�.");
					}
				} 
				else if (e.getSource() == Play_Btn) {
					if (Play_Btn.getIcon() == Play) {
						if(player.stateCode == player.STATE_SUSPENDED){
							player.resume(); // ���� ������ �Ͻ� ���� ���¿��ٸ� �簳
							Play_Btn.setIcon(Play_press);
							Play_Btn.setRolloverIcon(Pause_roll);
						}
						else{
						player.stop(); // ���� ������ �������¿��µ� �ٽ� ���� ��ư�� �����ٸ� �ٽ� ����
						player.Open(SongFile);
						player.stateCode = player.STATE_INIT;
						player.start();
						Play_Btn.setIcon(Play_press);
						Play_Btn.setRolloverIcon(Pause_roll);
					} 
					}
					else if (Play_Btn.getIcon() == Play_press) { // ������ ���� ���� �Ͻ� ���� ��ư�� �����ٸ� ���� �Ͻ� ����
						player.suspend();
						Play_Btn.setIcon(Play);
						Play_Btn.setRolloverIcon(Play_roll);
					
					}
				}
				else if(e.getSource() == Stop_Btn){ // ���� ��ư �����ٸ� ����
					player.stop();
					Play_Btn.setIcon(Play);
					Play_Btn.setRolloverIcon(Play_roll);
				}
				else if(e.getSource() == Before_Btn){ // ������ư �����ٸ� ������ ����
					player.stop();
					new PlaySongFrame(Emotion);
					JFP.dispose();
				}
				else if (e.getSource() == Next_Btn) { // ������ư �����ٸ� ������ ����
					player.stop();
					new PlaySongFrame(Emotion);
					JFP.dispose();
				}
			}
		};

		Return_Btn.addActionListener(listener);
		Comment_Btn.addActionListener(listener);
		Play_Btn.addActionListener(listener);
		Stop_Btn.addActionListener(listener);
		Before_Btn.addActionListener(listener);
		Next_Btn.addActionListener(listener);
		JFP.setVisible(true);
		JFP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
