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
	Bitmap bmpBackMainView;
	Bitmap bmpBackGameDescView;
	Bitmap bmpBackCharDescView;
	Bitmap bmpBackGameRoomView;
	Bitmap bmpBackGameConfigView;
	Bitmap bmpStart;			//開始按鈕圖片
	Bitmap bmpQuit;				//退出按鈕圖片
	Bitmap bmpConfig;			//設定按鈕圖片
	Bitmap bmpExit;				//Exit Button
	Bitmap bmpNext;
	Bitmap bmpSoundOn, bmpSoundOff;
	Bitmap bmpMusicOn, bmpMusicOff;
	int alpha = 255;			//透明度，初始為255，即不透明
	int view=-1;
	int start_x, start_y, quit_x, quit_y;
	int config_x, config_y, exit_x, exit_y;
	int music_x, music_y, sound_x, sound_y;
	int next_x, next_y;
	private int mWidth;
	private int mHeight;
	
	//建構式：初始化成員變數
	public MainView(CreativeMusem father) {
		super(father);
		this.father = father;
		getHolder().addCallback(this);	
		initBitmap(father);							//初始化圖片
		md_thread = new MainDrawThread(this,getHolder());	//新建MainDrawThread物件
		view = 0;//set to main view
		/* init game settings */
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
	    mWidth = MainView.MeasureSpec.getSize(widthMeasureSpec);
	    mHeight = MainView.MeasureSpec.getSize(heightMeasureSpec);

	    setMeasuredDimension(mWidth, mHeight);
	}
	public void initBitmap(Context context){//初始化圖片
		Resources r = context.getResources();			//獲取Resources物件
		bmpBackMainView = BitmapFactory.decodeResource(r, R.drawable.bg_main_view);
		bmpBackGameDescView = BitmapFactory.decodeResource(r, R.drawable.bg_game_desc_view);
		bmpBackCharDescView = BitmapFactory.decodeResource(r, R.drawable.bg_char_desc_view);
		bmpBackGameRoomView = BitmapFactory.decodeResource(r, R.drawable.bg_game_room_view);
		bmpBackGameConfigView = BitmapFactory.decodeResource(r, R.drawable.bg_game_config_view);
		bmpStart = BitmapFactory.decodeResource(r, R.drawable.game_start);
		bmpQuit = BitmapFactory.decodeResource(r, R.drawable.game_exit);
		bmpExit = BitmapFactory.decodeResource(r, R.drawable.exit);
		bmpNext = BitmapFactory.decodeResource(r, R.drawable.next);
		bmpConfig = BitmapFactory.decodeResource(r, R.drawable.game_configuration);
		bmpMusicOn = BitmapFactory.decodeResource(r, R.drawable.music_on);
		bmpMusicOff = BitmapFactory.decodeResource(r, R.drawable.music_off);
		bmpSoundOn = BitmapFactory.decodeResource(r, R.drawable.sound_on);
		bmpSoundOff = BitmapFactory.decodeResource(r, R.drawable.sound_off);
	}

	public void doDraw(Canvas canvas) {//方法：用於根據不同狀態繪製螢幕
		Paint paint = new Paint();		//新建畫筆
		
		switch(view){
		case 0://Main View
			canvas.drawColor(Color.BLACK);			//Clear screen
			paint.setAlpha(alpha);				//設定透明度
			canvas.drawBitmap(bmpBackMainView, 0, 0, paint);	//draw background
			config_x = (mWidth - bmpConfig.getWidth()) / 2;//center
			config_y = (mHeight - bmpConfig.getHeight()) / 2;//center
			start_x = (mWidth - bmpStart.getWidth()) / 2;
			start_y = config_y - bmpStart.getHeight();
			quit_x = (mWidth - bmpQuit.getWidth()) / 2;
			quit_y = config_y + bmpConfig.getHeight();

			canvas.drawBitmap(bmpStart, start_x, start_y, paint);//1st picture
			canvas.drawBitmap(bmpConfig, config_x, config_y, paint);//2nd picture
			canvas.drawBitmap(bmpQuit, quit_x, quit_y , paint);//3rd picture
			break;
		case 1://Game Description View
			canvas.drawColor(Color.BLACK);			//Clear screen
			canvas.drawBitmap(bmpBackGameDescView, 0, 0, paint);	//draw background

			exit_x = (mWidth - bmpExit.getWidth()) / 2;//center
			exit_y = (mHeight - bmpExit.getHeight()) / 2;//center
			next_x = (mWidth - bmpNext.getWidth()) / 2;
			next_y = exit_y - bmpNext.getHeight();
			canvas.drawBitmap(bmpNext, next_x, next_y, paint);//1st picture
			canvas.drawBitmap(bmpExit, exit_x, exit_y, paint);//2nd picture
			break;
		case 2://Character Description View
			canvas.drawColor(Color.BLACK);			//Clear screen
			canvas.drawBitmap(bmpBackCharDescView, 0, 0, paint);	//draw background

			exit_x = (mWidth - bmpExit.getWidth()) / 2;//center
			exit_y = (mHeight - bmpExit.getHeight()) / 2;//center
			next_x = (mWidth - bmpNext.getWidth()) / 2;
			next_y = exit_y - bmpNext.getHeight();
			canvas.drawBitmap(bmpNext, next_x, next_y, paint);//1st picture
			canvas.drawBitmap(bmpExit, exit_x, exit_y, paint);//2nd picture
			break;
		case 3://Game Room Description View
			canvas.drawColor(Color.BLACK);			//Clear screen
			canvas.drawBitmap(bmpBackGameRoomView, 0, 0, paint);	//draw background

			exit_x = (mWidth - bmpExit.getWidth()) / 2;//center
			exit_y = (mHeight - bmpExit.getHeight()) / 2;//center
			start_x = (mWidth - bmpStart.getWidth()) / 2;
			start_y = exit_y - bmpStart.getHeight();
			canvas.drawBitmap(bmpStart, start_x, start_y, paint);//1st picture
			canvas.drawBitmap(bmpExit, exit_x, exit_y, paint);//2nd picture
			break;
		case 4://Game Configuration View
			canvas.drawColor(Color.BLACK);			//Clear screen
			canvas.drawBitmap(bmpBackGameConfigView, 0, 0, paint);	//draw background
			exit_x = (mWidth - bmpExit.getWidth()) / 2;//center
			exit_y = (mHeight - bmpExit.getHeight()) / 2;//center
			sound_x = (mWidth - bmpSoundOn.getWidth()) / 2;
			sound_y = exit_y - bmpSoundOn.getHeight();
			music_x = (mWidth - bmpMusicOn.getWidth()) / 2;
			music_y = sound_y - bmpMusicOn.getHeight();
			if(father.wantMusic){//1st picture
				canvas.drawBitmap(bmpMusicOff, music_x, music_y, paint);
			}else{
				canvas.drawBitmap(bmpMusicOn, music_x, music_y, paint);
			}

			if(father.wantSound){//2nd picture
				canvas.drawBitmap(bmpSoundOff, sound_x, sound_y, paint);
			}else{
				canvas.drawBitmap(bmpSoundOn, sound_x, sound_y, paint);
			}

			canvas.drawBitmap(bmpExit, exit_x, exit_y, paint);//3rd picture
			break;
		default:
			break;
		}
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
