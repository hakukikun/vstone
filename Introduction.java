package jp.vstone.sotaplay;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import jp.vstone.RobotLib.*;
import jp.vstone.camera.CRoboCamera;
import jp.vstone.camera.CameraCapture;
import jp.vstone.camera.FaceDetectResult;

import jp.co.nttit.speechrec.Nbest;
import jp.vstone.RobotLib.CPlayWave;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotPose;
import jp.vstone.RobotLib.CRobotUtil;
import jp.vstone.RobotLib.CSotaMotion;
import jp.vstone.sotatalk.SpeechRecog;
import jp.vstone.sotatalk.TextToSpeechSota;
import jp.vstone.sotatalk.SpeechRecog.RecogResult;


public class Introduction {
	static final String TAG = "Introduction";
	public static void main(String[] args) {
		CRobotPose pose = new CRobotPose();
		
		
		//VSMDと通信ソケット・メモリアクセス用クラス
		CRobotMem mem = new CRobotMem();
		//Sota用モーション制御クラス
		CSotaMotion motion = new CSotaMotion(mem);
		
		
		
		//遷移時間1000msecで動作開始。
		motion.play(pose,500);
		pose.setLED_Sota(Color.WHITE, Color.WHITE, 255, Color.WHITE);
		
		CRobotUtil.wait(2000);
		
		CPlayWave.PlayWave(TextToSpeechSota.getTTS("こんにちは！"),true);
		pose.setLED_Sota(Color.BLUE, Color.BLUE, 255, Color.BLUE);
		CPlayWave.PlayWave(TextToSpeechSota.getTTS("僕の飼い主、ぐっちについて話すね。ぐっちは今年の9月に修士2年に無事、進級が出来たみたいでちょっとうれしそうだったよ。"),true);
		CPlayWave.PlayWave(TextToSpeechSota.getTTS("趣味はヴァイオリンとダブルダッチなんだけれど、最近はあまり、できていなさそう。お手柔らかに、よろしくお願いします！"),true);
		
		
		SpeechRecog recog = new SpeechRecog(motion);
		RecogResult result = recog.getRecognition(20000);
		pose.setLED_Sota(Color.WHITE, Color.WHITE, 255, Color.WHITE);
		
			if(result.getBasicResult().contains("ありがとう")){		
				pose.setLED_Sota(Color.BLUE, Color.BLUE, 255, Color.BLUE);
				CPlayWave.PlayWave(TextToSpeechSota.getTTSFile("僕もすごく緊張したよ！"),true);	
			}
			
		pose.setLED_Sota(Color.BLUE, Color.BLUE, 255, Color.BLUE);
		CPlayWave.PlayWave(TextToSpeechSota.getTTS("それではみなさま、よろしくお願いします！"),true);
		pose.setLED_Sota(Color.WHITE, Color.WHITE, 255, Color.WHITE);
		
		//遷移時間1000msecで動作開始。
		motion.play(pose,500);
		

	}
}