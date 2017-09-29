public class gasketClass{
	int x;
	int y;
	int sub;
	int i;
	int j;

	gasketClass(int newX, int newY, int newSub){
		x = newX;
		y = newY;
		sub = newSub;
		i = 0;
		j = 0;
}
	public int[] getGasketInfo(){
	  	int[] arr = {x, y, sub, i, j};
		return arr;
	}
	public int getX(){
	  return x;
	}
	public int getY(){
	  return j;
	}
	public int getSub(){
	  return sub;
	}
	public int getI(){
	  return i;
	}
	public int getJ(){
	  return j;
	}
	public void setI(int I){
	  i = I;
	}
	public void setJ(int J){
	  j = J;
	}
}
