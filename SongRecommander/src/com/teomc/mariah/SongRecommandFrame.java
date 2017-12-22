package com.teomc.mariah;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class SongRecommandFrame extends JFrame {
	private JTextField SongName_TField;
	private JTextField Singer_TField;
	private String ImgfilePath; // 이미지 파일 경로
	private String SongfilePath; // 노래 파일 경로

	public SongRecommandFrame() {

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame JFS = new JFrame("내가 추천하는 노래");
		JFS.getContentPane().setLayout(null);
		JFS.getContentPane().setBackground(Color.WHITE);
		JFS.setSize(res.width, res.height);
		JFS.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JFS.setResizable(false);
		JFS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton Return_Btn = new JButton("\uB3CC\uC544\uAC00\uAE30");
		Return_Btn.setForeground(Color.WHITE);
		Return_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Return_Btn.setFocusPainted(false);
		Return_Btn.setBackground(Color.BLACK);
		Return_Btn.setBounds(12, 10, 300, 150);
		JFS.getContentPane().add(Return_Btn);

		JButton SongFile_Btn = new JButton("\uC74C\uC6D0 \uD30C\uC77C");
		SongFile_Btn.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		SongFile_Btn.setBackground(Color.WHITE);
		SongFile_Btn.setBounds(542, 510, 233, 133);
		SongFile_Btn.setFocusPainted(false);
		// SongFile_Btn.setContentAreaFilled(false);
		// SongFile_Btn.setBorderPainted(false);
		JFS.getContentPane().add(SongFile_Btn);

		JButton ImgFile_Btn = new JButton("\uC790\uCF13 \uC0AC\uC9C4");
		ImgFile_Btn.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		ImgFile_Btn.setFocusPainted(false);
		ImgFile_Btn.setBackground(Color.WHITE);
		ImgFile_Btn.setBounds(542, 678, 233, 133);
		ImgFile_Btn.setFocusPainted(false);
		JFS.getContentPane().add(ImgFile_Btn);

		JButton Recommand_Btn = new JButton("\uCD94\uCC9C\uD558\uAE30");
		Recommand_Btn.setForeground(Color.WHITE);
		Recommand_Btn.setFont(new Font("HY목각파임B", Font.ITALIC, 60));
		Recommand_Btn.setFocusPainted(false);
		Recommand_Btn.setBackground(Color.BLACK);
		Recommand_Btn.setBounds(983, 859, 300, 150);
		JFS.getContentPane().add(Recommand_Btn);

		JLabel SongFile_Label = new JLabel("");
		SongFile_Label.setForeground(Color.WHITE);
		SongFile_Label.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		SongFile_Label.setOpaque(true); // 라벨 색상 변경을 위한 것
		SongFile_Label.setBackground(Color.BLACK);
		SongFile_Label.setBounds(777, 597, 514, 46);
		JFS.getContentPane().add(SongFile_Label);

		JLabel SongName_Label = new JLabel("\uB178\uB798 \uC81C\uBAA9");
		SongName_Label.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		SongName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		SongName_Label.setBounds(532, 81, 233, 133);
		JFS.getContentPane().add(SongName_Label);

		JLabel Singer_Label = new JLabel("\uAC00\uC218");
		Singer_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Singer_Label.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		Singer_Label.setBounds(542, 224, 233, 133);
		JFS.getContentPane().add(Singer_Label);

		JLabel Emotion_Label = new JLabel("\uB4E3\uB294 \uAE30\uBD84");
		Emotion_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Emotion_Label.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		Emotion_Label.setBounds(532, 367, 233, 133);
		JFS.getContentPane().add(Emotion_Label);

		JLabel ImgFile_Label = new JLabel("");
		ImgFile_Label.setOpaque(true);
		ImgFile_Label.setForeground(Color.WHITE);
		ImgFile_Label.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		ImgFile_Label.setBackground(Color.BLACK);
		ImgFile_Label.setBounds(777, 765, 514, 46);
		JFS.getContentPane().add(ImgFile_Label);

		SongName_TField = new JTextField();
		SongName_TField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		SongName_TField.setForeground(Color.WHITE);
		SongName_TField.setBackground(Color.BLACK);
		SongName_TField.setBounds(777, 132, 514, 46);
		JFS.getContentPane().add(SongName_TField);

		Singer_TField = new JTextField();
		Singer_TField.setForeground(Color.WHITE);
		Singer_TField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		Singer_TField.setColumns(10);
		Singer_TField.setBackground(Color.BLACK);
		Singer_TField.setBounds(777, 264, 514, 46);
		JFS.getContentPane().add(Singer_TField);

		String[] Emotion_List = { "머라이어 캐리", "즐거울 때", "우울할 때", "짜증날 때", "억울할 때", "화날 때", "보통일 때", "놀랐을 때", "슬플 때" };

		JComboBox Emotion_comboBox = new JComboBox(Emotion_List);
		Emotion_comboBox.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		Emotion_comboBox.setBounds(777, 405, 514, 46);
		JFS.getContentPane().add(Emotion_comboBox);

		JFileChooser SongfileChooser = new JFileChooser("C:"); // 파일 탐색(선택)기, 기본 경로 C:
		// SongfileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop ")); // 기본으로 뜰 파일 경로(바탕화면)
		FileNameExtensionFilter Song_filter = new FileNameExtensionFilter("mp3 파일", "mp3"); // 파일 확장자 제한
		SongfileChooser.addChoosableFileFilter(Song_filter);

		JFileChooser ImgfileChooser = new JFileChooser("C:"); // 파일 탐색(선택)기, 기본 경로 C:
		// ImgfileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop ")); // 기본으로 뜰 파일 경로(바탕화면)
		FileNameExtensionFilter Img_filter = new FileNameExtensionFilter("사진 파일", "png", "jpg"); // 파일확장자 제한
		ImgfileChooser.addChoosableFileFilter(Img_filter);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Return_Btn) {
					new MainFrame();
					JFS.dispose();
				} else if (e.getSource() == ImgFile_Btn) {
					int result = ImgfileChooser.showOpenDialog(JFS); // 열기를 누르면 0 반환, 취소를 누르면 1 반환
					if (result == 0) {
						ImgfilePath = ImgfileChooser.getSelectedFile().getAbsolutePath(); // 파일 경로 가져오기
						ImgFile_Label.setText(ImgfilePath);
					}
				} else if (e.getSource() == SongFile_Btn) {
					int result = SongfileChooser.showOpenDialog(JFS); // 열기를 누르면  0 반환, 취소를 누르면 1 반환
					if (result == 0) {
						SongfilePath = SongfileChooser.getSelectedFile().getAbsolutePath(); // 파일경로 가져오기
						SongFile_Label.setText(SongfilePath);
					}
				} else if (e.getSource() == Recommand_Btn) {
					if (ImgfilePath != null && SongfilePath != null) {
						try {
							String Emotion = Emotion_comboBox.getSelectedItem().toString(); // 콤보박스에 선택된 항목을 문자열 형태로 가져옴
							if (Emotion == "머라이어 캐리")
								Emotion = "Mariah";
							else if (Emotion == "즐거울 때")
								Emotion = "Happy";
							else if (Emotion == "우울할 때")
								Emotion = "Gloomy";
							else if (Emotion == "짜증날 때")
								Emotion = "Annoyance";
							else if (Emotion == "억울할 때")
								Emotion = "Depression";
							else if (Emotion == "화날 때")
								Emotion = "Angry";
							else if (Emotion == "보통일 때")
								Emotion = "Soso";
							else if (Emotion == "놀랐을 때")
								Emotion = "Shock";
							else if (Emotion == "슬플 때")
								Emotion = "Sad";

							String SongName = SongName_TField.getText();
							String Singer = Singer_TField.getText();
							new DBBean().Insert_DB(SongName, Singer, Emotion, SongfilePath, ImgfilePath);
							JOptionPane.showMessageDialog(null, "파일 업로드 성공");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "파일 업로드 실패");
					}
				}
			}
		};

		Return_Btn.addActionListener(listener);
		SongFile_Btn.addActionListener(listener);
		ImgFile_Btn.addActionListener(listener);
		Recommand_Btn.addActionListener(listener);

		JFS.setVisible(true);

	}

	public static void main(String[] args) {
		new SongRecommandFrame();
	}
}
