package qianlong.musemgame;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
/*
 * �����O�~�Ӧ�Thread�A�D�n�t�d�w�ɧ�sWelcomeView
 */
public class MainDrawThread extends Thread{
	MainView father;					//WelcomeView���󪺤ޥ�
	SurfaceHolder surfaceHolder;		//WelcomeView����SurfaceHolder
	int sleepSpan = 100;				//��v�ɶ�
	boolean flag;						//���������X�Ц줸
	//�غc���G��l�ƥD�n�������ܼ�
	public MainDrawThread(MainView father,SurfaceHolder surfaceHolder){
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		this.flag = true;
	}
	//��k�G����������k
	public void run(){
		Canvas canvas = null;			//�s�ؤ@��Canvas����
		while(flag){
			try{
				canvas = surfaceHolder.lockCanvas(null);//���e���[��
				synchronized(surfaceHolder){
					father.doDraw(canvas);				//���sø�s�ù�
				}
			}
			catch(Exception e){
				e.printStackTrace();//���򲧱`�æC�L
			}
			finally{
				if(canvas != null){//����e���ñN��Ǧ^
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			try{
				Thread.sleep(sleepSpan);		//��v�@�q�ɶ�
			}
			catch(Exception e){
				e.printStackTrace();			//���򲧱`�æC�L
			}
		}
	}
}