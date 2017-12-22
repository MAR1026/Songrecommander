package com.teomc.mariah;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import javazoom.jl.player.AudioDevice; 
import javazoom.jl.decoder.*;
import javazoom.jl.player.FactoryRegistry;
// �ܺ��б���, header, Decoder, AudioDecoder, samplebuffer, bitstream Ŭ���� ���� �������

public class MusicPlay implements Runnable{ // �뷡 ���� ���, ������ �̿�, �뷡�� ���ڵ��Ͽ� ��Ʈ������ �߰� �ɰ��� ���ۿ� �����ϸ鼭 ����, http://blog.naver.com/hakyunghn/220398300176 ����
	DBBean DB = new DBBean();
	
	static class Sample{
		private short [] buffer; // ���ڵ� �� �뷡�� ��Ʈ�� ������ ����
		private int length; // ���ڵ� �� �뷡�� ����
		
		public Sample(short[] buf, int s){ // �������̵�(�����)
			buffer = buf.clone(); // ���ڵ� �� �뷡�� ��Ʈ 1���� �����ؼ� ����
			length = s; // �뷡�� ����
		}
		
		public short[] getBuffer(){
			return buffer; // ���ۿ� ����� ��Ʈ ��ȯ
		}
		
		public int GetLength(){
			return length; // ���ۿ� ����� �뷡 ���� ��ȯ
		}
	}
	
	public static final int BUFFER_SIZE = 20000; // ���� ũ�� ����
	
	private Decoder decoder; // ���ڵ� ���ִ� ��
	private AudioDevice out;  // �뷡 ��� ���ִ� ��
	private ArrayList<Sample> playes; // ���׸�, �뷡 ������ ���� �����
	private int length; // ����
	
	public boolean IsInvalid(){
		return (decoder == null || out == null || playes == null || !out.isOpen()); // �뷡�� ���� ���� ����(���ۿ� �ƹ��͵� ����)�̸� 1 ��ȯ ���ִ� ��
	}
	
	protected boolean Getplays(String path){ // 
		if(IsInvalid())
			return false; 
		try{
			Header header; // �� ��Ʈ ��ġ�� ����Ŵ
			SampleBuffer pb; // ��� ����, ������ ũ��� �߶� ������
			FileInputStream in = new FileInputStream(path); // ���� �ּ� �޾ƿͼ� ����Ʈ ������ ��������
			Bitstream bitstream = new Bitstream(in); // ����� ������ �����ͼ� �м��ϴ� ��
			if((header = bitstream.readFrame()) == null)
				return false;
			while(length < BUFFER_SIZE && header != null){
				pb = (SampleBuffer)decoder.decodeFrame(header, bitstream); // ���ڵ� �� �뷡�� �����ͼ� ���� ������ ����
				playes.add(new Sample(pb.getBuffer(), pb.getBufferLength())); // ��Ʈ�� �Ѱ��� �߰��ؼ� �뷡�� ��� ��Ʈ�� ����
				length++; // ���̸� �ø�
				bitstream.closeFrame(); // ���� MP3 �������� ����
				header = bitstream.readFrame(); // �� MP3 ������ ��������
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean Open(String path){ // ���� �ּ� �����ͼ� ���� ���� ���� ��
		try{
			decoder = new Decoder(); // ������ ��Ʈ ������ �ɰ�
			out = FactoryRegistry.systemRegistry().createAudioDevice(); // ����� ��ġ �ν��Ͻ� ����
			playes = new ArrayList<Sample>(BUFFER_SIZE); // ���׸�, ���� ������ ��ŭ ��Ʈ�� �����ϴ� ���ۿ� �� ���̸� ���� ������ ����
			length = 0; // ���� �ʱ�ȭ
			out.open(decoder); // ���� ����� ���� �غ�
			Getplays(path); // ������ ���ۿ� �����ϴ� �޼ҵ�� ����
		} catch (Exception e) {
			decoder = null;
			out = null;
			playes = null;
			return false;
		}
		return true;
	}
	
	private Thread thisThread; // ������ ����
	
	final static int STATE_INIT = 0; 
	final static int STATE_STARTED = 1;
	final static int STATE_SUSPENDED = 2;
	final static int STATE_STOPPED = 3;
	// ���� ������ ���¸� �˱� ���� ����� ���
	
	static int stateCode = STATE_INIT;
	// ���� ���¸� �����ϴ� ����
	
	public void start(){ // ������ ����, �������̵�
		synchronized (this) { // ����ȭ, �ѹ��� �ϳ��� �����常 ���� ����
			thisThread = new Thread(this); // �Լ� ���� ������ ������� ����
			thisThread.start(); // ���� �����带 ����, ������ �����尡 Ȱ�� ����
			stateCode = STATE_STARTED; // �����尡 ���� ���� �ڵ带 ��� ���·� �ٲ�
		}
	}
	
	public void run(){ // ���� ���̵�
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
			this.stateCode = STATE_STOPPED; // �����尡 ������ �ִ� ���� �ڵ带 ���� ���·� ����
			try{
				thisThread.sleep(100); // ������ Ȱ�� ����
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("����");
		}
	}
	
	@SuppressWarnings("static-access")
	public void play(){
		if(IsInvalid()){
			return; // ���� ������ �����Ƿ� ��� X
		}
		try{
			for(int i = 0; i<length; i++){
				out.write(playes.get(i).getBuffer(), 0, playes.get(i).GetLength()); // ���ۿ��� ��Ʈ�� ���̸� �޾ƿͼ� ����
			if(stateCode == STATE_STOPPED){
				Close(); // ���� �� ���� �ڵ尡 �������� �ٲ�� ���� �޼ҵ� ȣ��
			}
			if (stateCode == STATE_SUSPENDED){ // ���� �� ���� �ڵ尡 �Ͻ� ������ �ٲ�� �����带 ���� ��Ŵ
				System.out.println("�Ͻ� ����"); 
				while(true){
					try {
						thisThread.sleep(100); // �����带 Ȱ�� ���� ��Ŵ
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
					if( stateCode == STATE_STARTED || stateCode == STATE_STOPPED){ // �����ڵ尡 �ٲ�� �����尡 ���
						break;
					}
				}
			}
		}
	} catch (JavaLayerException e) {}
		Close();
	}
	
	public void Close(){ // ���� ������ ������
		if((out != null) && !out.isOpen()){
			out.close();
		}
		length = 0;
		playes = null;
		out = null;
		decoder = null;
	}
	
	public void suspend(){ // �Ͻ� ���� �޼ҵ�
		if (stateCode == STATE_SUSPENDED){
			return;
		}else if (stateCode == STATE_INIT){
			System.out.println("�뷡�� ���� ���� �ƴմϴ�.");
		}else if (stateCode == STATE_STOPPED){
			System.out.println("�뷡�� ���� �����Դϴ�.");
		}else{ // �뷡�� ���� ���̿��ٸ� ���� �ڵ带 �Ͻ� ������ ���� 
			System.out.println("�Ͻ� ����");
			stateCode = STATE_SUSPENDED; 
		}
	}
	
	public void resume() { // �Ͻ� ���� ���¿��ٸ� �簳�ϴ� �޼ҵ�
		if(stateCode == STATE_STARTED || stateCode == STATE_INIT){
			System.out.println("���� ���� �ƴմϴ�.");
			return;
		}
		if(stateCode == STATE_STOPPED){
			System.out.println("���� ���� �Դϴ�.");
		}
		stateCode = STATE_STARTED;
		System.out.println("�簳");
	}
}
