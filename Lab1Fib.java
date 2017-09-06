public class Lab1Fib{
	public static void main(String[] args) {
		System.out.println(recursiveFib(10));
		System.out.println(loopFib(10));
	}

	public static int recursiveFib(int n){
		if(n<2)
			return 1;
		return recursiveFib(n-1)+recursiveFib(n-2);
	}

	public static int loopFib(int n){
		if(n<=1)
			return n;
		int f1 = 1;
		int f2 = 1;
		int fn = 1;
		
		for(int i = 2; i<=n; i++) //first we calculate the second fib
		{
			fn = f1+f2; //calculates fib number i
			f1 = f2;
			f2 = fn;
		}

		return(fn);

	}

	public static int constantFib(int n){
		return (Math.pow((1+Math.sqrt(5))/2, n) - Math.pow((1-Math.sqrt(5))/2, n))/Math.sqrt(5);
	}
}

	
