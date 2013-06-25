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
	MainDrawThread md_thread;			//��O��ø�����
	CreativeMusem father;		//Activity���ޥ�
	Bitmap bmpBack;				//�I���Ϥ�
	Bitmap bmpStart;			//�}�l���s�Ϥ�
	Bitmap bmpQuit;				//�h�X���s�Ϥ�
	Bitmap bmpConfig;			//�]�w���s�Ϥ�
	int alpha = 255;			//�z���סA��l��255�A�Y���z��
	
	//�غc���G��l�Ʀ����ܼ�
	public MainView(CreativeMusem father) {
		super(father);
		this.father = father;
		getHolder().addCallback(this);	
		initBitmap(father);							//��l�ƹϤ�
		md_thread = new MainDrawThread(this,getHolder());	//�s��MainDrawThread����
		/* init game settings */
	}

	public void initBitmap(Context context){//��l�ƹϤ�
		Resources r = context.getResources();			//���Resources����
		bmpBack = BitmapFactory.decodeResource(r, R.drawable.welcome);		//�s�حI���Ϥ�
		bmpStart = BitmapFactory.decodeResource(r, R.drawable.start);		//�s�ض}�l�Ϥ����s
		bmpQuit = BitmapFactory.decodeResource(r, R.drawable.quit);			//�s�ص����Ϥ����s
		bmpConfig = BitmapFactory.decodeResource(r, R.drawable.config);		//�s�س]�w�Ϥ����s
	}

	public void doDraw(Canvas canvas) {//��k�G�Ω�ھڤ��P���Aø�s�ù�
		Paint paint = new Paint();		//�s�صe��
		
		canvas.drawColor(Color.BLACK);			//�M�ù�
		paint.setAlpha(alpha);					//�]�w�z����
		canvas.drawBitmap(bmpBack, 0, 0, paint);//�e�I��
		
		canvas.drawBitmap(bmpStart, 205, 425, paint);						//ø�s�}�l���s
		canvas.drawBitmap(bmpQuit, 205, 525, paint);						//ø�s�h�X���s
		canvas.drawBitmap(bmpConfig, 25, 425, paint);						//ø�s�]�w���s
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {		
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(!md_thread.isAlive()){
			md_thread.start();
		}			 
        //father.pmt = new PlayerMoveThread(father);//��l�ƨñҰʲy�������ʳB�z�����
        //father.pmt.start();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//dt.isGameOn = false;	//�����s�ù������������
		//father.pmt.flag = false;
		if(md_thread.isAlive()){				//�����Oø�s�����
			md_thread.flag = false;
		}
	}
}