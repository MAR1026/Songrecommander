package com.teomc.mariah;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import javazoom.jl.player.AudioDevice; 
import javazoom.jl.decoder.*;
import javazoom.jl.player.FactoryRegistry;
// 외부패기지, header, Decoder, AudioDecoder, samplebuffer, bitstream 클래스 등이 들어있음

public class MusicPlay implements Runnable{ // 노래 실제 재생, 쓰레드 이용, 노래를 디코딩하여 비트단위로 잘개 쪼개서 버퍼에 저장하면서 실행, http://blog.naver.com/hakyunghn/220398300176 참고
	DBBean DB = new DBBean();
	
	static class Sample{
		private short [] buffer; // 디코딩 된 노래의 비트를 저장할 버퍼
		private int length; // 디코딩 된 노래의 길이
		
		public Sample(short[] buf, int s){ // 오버라이딩(덮어쓰기)
			buffer = buf.clone(); // 디코딩 된 노래의 비트 1개를 복사해서 저장
			length = s; // 노래의 길이
		}
		
		public short[] getBuffer(){
			return buffer; // 버퍼에 저장된 비트 반환
		}
		
		public int GetLength(){
			return length; // 버퍼에 저장된 노래 길이 반환
		}
	}
	
	public static final int BUFFER_SIZE = 20000; // 버퍼 크기 지정
	
	private Decoder decoder; // 디코딩 해주는 애
	private AudioDevice out;  // 노래 출력 해주는 애
	private ArrayList<Sample> playes; // 제네릭, 노래 음원이 여기 저장됨
	private int length; // 길이
	
	public boolean IsInvalid(){
		return (decoder == null || out == null || playes == null || !out.isOpen()); // 노래가 완전 정지 상태(버퍼에 아무것도 없음)이면 1 반환 해주는 애
	}
	
	protected boolean Getplays(String path){ // 
		if(IsInvalid())
			return false; 
		try{
			Header header; // 현 비트 위치를 가리킴
			SampleBuffer pb; // 출력 버퍼, 고정된 크기로 잘라서 가져옴
			FileInputStream in = new FileInputStream(path); // 파일 주소 받아와서 바이트 단위로 가져오기
			Bitstream bitstream = new Bitstream(in); // 오디오 파일을 가져와서 분석하는 애
			if((header = bitstream.readFrame()) == null)
				return false;
			while(length < BUFFER_SIZE && header != null){
				pb = (SampleBuffer)decoder.decodeFrame(header, bitstream); // 디코딩 된 노래를 가져와서 일정 단위로 저장
				playes.add(new Sample(pb.getBuffer(), pb.getBufferLength())); // 비트를 한개씩 추가해서 노래의 모든 비트를 붙임
				length++; // 길이를 늘림
				bitstream.closeFrame(); // 현재 MP3 프레임을 닫음
				header = bitstream.readFrame(); // 새 MP3 프레임 가져오기
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean Open(String path){ // 파일 주소 가져와서 실제 파일 여는 애
		try{
			decoder = new Decoder(); // 음원을 비트 단위로 쪼갬
			out = FactoryRegistry.systemRegistry().createAudioDevice(); // 오디오 장치 인스턴스 구현
			playes = new ArrayList<Sample>(BUFFER_SIZE); // 제네릭, 버퍼 사이즈 만큼 비트를 저장하는 버퍼와 그 길이를 담은 변수를 생성
			length = 0; // 길이 초기화
			out.open(decoder); // 음원 재생을 위해 준비
			Getplays(path); // 파일을 버퍼에 저장하는 메소드로 보냄
		} catch (Exception e) {
			decoder = null;
			out = null;
			playes = null;
			return false;
		}
		return true;
	}
	
	private Thread thisThread; // 스레드 생성
	
	final static int STATE_INIT = 0; 
	final static int STATE_STARTED = 1;
	final static int STATE_SUSPENDED = 2;
	final static int STATE_STOPPED = 3;
	// 현재 음원의 상태를 알기 위해 선언된 상수
	
	static int stateCode = STATE_INIT;
	// 음원 상태를 저장하는 변수
	
	public void start(){ // 스레드 시작, 오버라이딩
		synchronized (this) { // 동기화, 한번에 하나의 스레드만 접근 가능
			thisThread = new Thread(this); // 함수 내로 접근한 스레드로 설정
			thisThread.start(); // 실제 스레드를 생성, 생성된 스레드가 활동 시작
			stateCode = STATE_STARTED; // 스레드가 가진 상태 코드를 재상 상태로 바꿈
		}
	}
	
	public void run(){ // 오버 라이딩
		while(true){
			if ( stateCode == STATE_STOPPED){
				break;
			}
			play();
		}
	}
	
	@SuppressWarnings("static-access")
	public void stop() {
		synchronized (this) {
			this.stateCode = STATE_STOPPED; // 스레드가 가지고 있는 상태 코드를 정지 상태로 변경
			try{
				thisThread.sleep(100); // 스레드 활동 정지
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("정지");
		}
	}
	
	@SuppressWarnings("static-access")
	public void play(){
		if(IsInvalid()){
			return; // 음원 파일이 없으므로 재생 X
		}
		try{
			for(int i = 0; i<length; i++){
				out.write(playes.get(i).getBuffer(), 0, playes.get(i).GetLength()); // 버퍼에서 비트와 길이를 받아와서 송출
			if(stateCode == STATE_STOPPED){
				Close(); // 송출 중 상태 코드가 멈춤으로 바뀌면 정지 메소드 호출
			}
			if (stateCode == STATE_SUSPENDED){ // 송출 중 상태 코드가 일시 정지로 바뀌면 스레드를 정지 시킴
				System.out.println("일시 정지"); 
				while(true){
					try {
						thisThread.sleep(100); // 스레드를 활동 정지 시킴
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
					if( stateCode == STATE_STARTED || stateCode == STATE_STOPPED){ // 상태코드가 바뀌면 스레드가 깨어남
						break;
					}
				}
			}
		}
	} catch (JavaLayerException e) {}
		Close();
	}
	
	public void Close(){ // 음원 파일을 제거함
		if((out != null) && !out.isOpen()){
			out.close();
		}
		length = 0;
		playes = null;
		out = null;
		decoder = null;
	}
	
	public void suspend(){ // 일시 정지 메소드
		if (stateCode == STATE_SUSPENDED){
			return;
		}else if (stateCode == STATE_INIT){
			System.out.println("노래가 실행 중이 아닙니다.");
		}else if (stateCode == STATE_STOPPED){
			System.out.println("노래가 정지 상태입니다.");
		}else{ // 노래가 실행 중이였다면 상태 코드를 일시 정지로 변경 
			System.out.println("일시 정지");
			stateCode = STATE_SUSPENDED; 
		}
	}
	
	public void resume() { // 일시 정지 상태였다면 재개하는 메소드
		if(stateCode == STATE_STARTED || stateCode == STATE_INIT){
			System.out.println("실행 중이 아닙니다.");
			return;
		}
		if(stateCode == STATE_STOPPED){
			System.out.println("정지 상태 입니다.");
		}
		stateCode = STATE_STARTED;
		System.out.println("재개");
	}
}
