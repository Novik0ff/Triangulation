package Calculate;

public class Point {
    private double x,y;
    public Point(){
        x=0;
        y=0;
    }
    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    boolean equals(Point point){
        if(point.getX() != this.getX() && point.getY() != this.getY()){
            return false;
        }
        else{
            return true;
        }
    }
}
