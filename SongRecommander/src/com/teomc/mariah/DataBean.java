package com.teomc.mariah;

public class DataBean {
		private int SongId; // �뷡 ��ȣ
		private String SongName; // �뷡 �̸�
		private String Singer; // �뷡 ����
		private String Emotion; // �뷡 ��� ���
		private String SongFile; // �뷡 ���� �ּ�
		private String ImgFile; // �ٹ� Ŀ�� ���� �ּ�
		

		public int getSongId() {
			return SongId;
		}
		public void setSongId(int songId) {
			SongId = songId;
		}
		public String getSongName() {
			return SongName;
		}
		public void setSongName(String songName) {
			SongName = songName;
		}
		public String getSinger() {
			return Singer;
		}
		public void setSinger(String singer) {
			Singer = singer;
		}
		public String getEmotion() {
			return Emotion;
		}
		public void setEmotion(String emotion) {
			Emotion = emotion;
		}
		public String getSongFile() {
			return SongFile;
		}
		public void setSongFile(String songFile) {
			SongFile = songFile;
		}
		public String getImgFile() {
			return ImgFile;
		}
		public void setImgFile(String imgFile) {
			ImgFile = imgFile;
		}
}
