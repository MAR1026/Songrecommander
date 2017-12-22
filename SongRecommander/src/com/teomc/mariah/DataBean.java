package com.teomc.mariah;

public class DataBean {
		private int SongId; // 노래 번호
		private String SongName; // 노래 이름
		private String Singer; // 노래 가수
		private String Emotion; // 노래 듣는 기분
		private String SongFile; // 노래 파일 주소
		private String ImgFile; // 앨범 커버 파일 주소
		

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
