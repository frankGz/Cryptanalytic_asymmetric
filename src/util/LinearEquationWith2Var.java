package util;

public class LinearEquationWith2Var {

	public static void main(String[] args) {
		/*
		 * a1x+b1y = c1
		 * a2x+b2y = c2
		 * 
		 * x = 3 y = 5
		 * 
		 * 3x + 5y = 34
		 * x - 2y = -7
		 */
		solveSimultaneousEquations(3, 5, 34, 1, -2, -7);
	}
	
	public static void solveSimultaneousEquations(double a1, double b1, double c1, double a2, double b2, double c2) {
	    
	    double x = (((c1)*(b2)) - ((c2)*(b1))) / (((a1)*(b2)) - ((a2)*(b1)));
	    double y = (c1*a2 - c2*a1) / (b1*a2 - b2*a1);
	    System.out.print("x=" + x + " y=" + y);
	}

}
