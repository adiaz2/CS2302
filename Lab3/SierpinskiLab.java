import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Stack;


@SuppressWarnings("serial")
public class SierpinskiLab extends JFrame {

	private Graphics2D g2d;

	public SierpinskiLab() {
		super("Sierpinski Lab");

		getContentPane().setBackground(Color.BLACK);
		setSize(729, 729);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	void drawRectangles(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setPaint(Color.GRAY);
		drawGasketStack(0, 0, 729);
	}

	public void drawGasket(int x, int y, int side) {
		int sub = side / 3; 

		//Draw center square
		g2d.fill(new Rectangle2D.Double(x + sub, y + sub, sub - 1, sub - 1));

		if(sub >= 3) {
			//Draw 8 surrounding squares
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					if (j!=1 || i != 1)
						drawGasket(x + i * sub, y + j * sub, sub);
				}
			}
		}
	}
	public void drawGasketStack(int x, int y, int side){
		Stack<gasketClass> st = new Stack();
		//initialize a new element in the stack with the given values of x, y, the sub (made by dividing side by 3)
		st.push(new gasketClass(x, y, side/3));

		//gasketInfo holds 0.x, 1.y, 2.sub, 3.i, 4.j
		int[] gasketInfo = new int[5];
		x = 0;
		y = 1;
		int sub = 2;
		//used to store the loop values of the previous element in the stack to create the new element in the stack
		int i=0;
		int j=0;


		while(!st.isEmpty()){
			//get the elements of the current top of the stack, this simulates being in a certain recursive call
		  	gasketInfo = st.peek().getGasketInfo();

			//we make sure that we only create a new rectangle if this is the first time we access this element in this stack
			if(st.peek().getI() == 0 && st.peek().getJ() == 0)
				g2d.fill(new Rectangle2D.Double(gasketInfo[x]+gasketInfo[sub],gasketInfo[y]+gasketInfo[sub],gasketInfo[sub]-1,gasketInfo[sub]-1));
	//This part mimics the for loop that makes the recursive calls.
	//Since the loop makes a recursive call if i and j are not both equal to 1,
	//we can add to the stack while this does not occur
			
			//if the sub is less than three, you can remove this element from the stack
			if(gasketInfo[sub] < 3)
			  st.pop();
			//if the i and j are currently both 1, we increase j by  1 and restart the loop
			else if(st.peek().getI() == 1 && st.peek().getJ() == 1)
			  st.peek().setJ(st.peek().getJ()+1);
			//if the loop of the item at the top of the stack is at its last iteration, then you pop the top
			else if(st.peek().getI() == 2 && st.peek().getJ() == 2){
			i = st.peek().getI();
			j = st.peek().getJ();
			st.pop();
			st.push(new gasketClass(gasketInfo[x]+i*gasketInfo[sub], gasketInfo[y]+j*gasketInfo[sub], gasketInfo[sub]/3));
		}
			//if and j is at the last iteration of its loop we increase j by 1
			else if(st.peek().getJ()==2) {
			i = st.peek().getI();
			j = st.peek().getJ();
			st.peek().setI(st.peek().getI()+1);
			st.peek().setJ(0);
			st.push(new gasketClass(gasketInfo[x]+i*gasketInfo[sub], gasketInfo[y]+j*gasketInfo[sub], gasketInfo[sub]/3));
			}	
			
			//if both i and j are 0, we move j up to j=2
			else{
			i = st.peek().getI();
			j = st.peek().getJ();
			st.peek().setJ(st.peek().getJ()+1);
			st.push(new gasketClass(gasketInfo[x]+i*gasketInfo[sub], gasketInfo[y]+j*gasketInfo[sub], gasketInfo[sub]/3));
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawRectangles(g);
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SierpinskiLab().setVisible(true);
			}
		});
	}

}
