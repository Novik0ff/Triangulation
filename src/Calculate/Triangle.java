package Calculate;

public class Triangle {
    private Point a,b,c;
    public Triangle(){

    }
    public Triangle(Point a, Point b, Point c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public Point getA(){
        return a;
    }
    public Point getB(){
        return b;
    }
    public Point getC(){
        return c;
    }
}
