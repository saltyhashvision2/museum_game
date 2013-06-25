package qianlong.musemgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback{
	MainDrawThread md_thread;			//後臺重繪執行緒
	CreativeMusem father;		//Activity的引用
	Bitmap bmpBack;				//背景圖片
	Bitmap bmpStart;			//開始按鈕圖片
	Bitmap bmpQuit;				//退出按鈕圖片
	Bitmap bmpConfig;			//設定按鈕圖片
	int alpha = 255;			//透明度，初始為255，即不透明
	
	//建構式：初始化成員變數
	public MainView(CreativeMusem father) {
		super(father);
		this.father = father;
		getHolder().addCallback(this);	
		initBitmap(father);							//初始化圖片
		md_thread = new MainDrawThread(this,getHolder());	//新建MainDrawThread物件
		/* init game settings */
	}

	public void initBitmap(Context context){//初始化圖片
		Resources r = context.getResources();			//獲取Resources物件
		bmpBack = BitmapFactory.decodeResource(r, R.drawable.welcome);		//新建背景圖片
		bmpStart = BitmapFactory.decodeResource(r, R.drawable.start);		//新建開始圖片按鈕
		bmpQuit = BitmapFactory.decodeResource(r, R.drawable.quit);			//新建結束圖片按鈕
		bmpConfig = BitmapFactory.decodeResource(r, R.drawable.config);		//新建設定圖片按鈕
	}

	public void doDraw(Canvas canvas) {//方法：用於根據不同狀態繪製螢幕
		Paint paint = new Paint();		//新建畫筆
		
		canvas.drawColor(Color.BLACK);			//清螢幕
		paint.setAlpha(alpha);					//設定透明度
		canvas.drawBitmap(bmpBack, 0, 0, paint);//畫背景
		
		canvas.drawBitmap(bmpStart, 205, 425, paint);						//繪製開始按鈕
		canvas.drawBitmap(bmpQuit, 205, 525, paint);						//繪製退出按鈕
		canvas.drawBitmap(bmpConfig, 25, 425, paint);						//繪製設定按鈕
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {		
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(!md_thread.isAlive()){
			md_thread.start();
		}			 
        //father.pmt = new PlayerMoveThread(father);//初始化並啟動球員的移動處理執行緒
        //father.pmt.start();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//dt.isGameOn = false;	//停止更新螢幕執行緒的執行
		//father.pmt.flag = false;
		if(md_thread.isAlive()){				//停止後臺繪製執行緒
			md_thread.flag = false;
		}
	}
}