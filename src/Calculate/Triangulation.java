package Calculate;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.shape.Circle;


public class Triangulation {
    public ArrayList<Point> pointCollection; //точки на плоскости
    public ArrayList<Triangle> triangleCollection; // треугольники
    public ArrayList<Circle> circleCollection;

    public Triangulation(int countPoints, int minX, int maxX, int minY, int maxY) {
        pointCollection = new ArrayList<>();
        triangleCollection = new ArrayList<>();
        circleCollection = new ArrayList<>();
        //задали случайные точки
        for (int i = 0; i < countPoints; i++) {
            Random rnd = new Random();
            pointCollection.add(new Point(Math.abs(rnd.nextDouble()*(maxX-minX) + minX), Math.abs(rnd.nextDouble()*(maxY-minY) + minY)));
        }
        //проверяем все наборы точек в коллекции
        for (int i = 0; i < pointCollection.size(); i++) {
            for (int j = 0; j < pointCollection.size(); j++) {
                for (int k = 0; k < pointCollection.size(); k++) {
                    if (i != j && i != k && j != k) {
                        mbAddTriangle(new Triangle(pointCollection.get(i), pointCollection.get(j), pointCollection.get(k)));
                    }
                }
            }
        }
    }

    /**
     * Функция для проверки треугольника на добавление
     *
     * @param triangle проверяемый треугольник
     */
    private void mbAddTriangle(Triangle triangle) {
        double tempCX; // координата - X центра окружности
        double tempCY; // координата - Y центра окружности
        double tempR; // радиус окружности
        boolean check = true; //проверка треугольника
        //вычислим координаты
        tempCX = 0.5 * ((triangle.getB().getX() * triangle.getB().getX() -
                triangle.getA().getX() * triangle.getA().getX() +
                triangle.getB().getY() * triangle.getB().getY() -
                triangle.getA().getY() * triangle.getA().getY()) * (triangle.getC().getY() - triangle.getA().getY()) -
                (triangle.getC().getX() * triangle.getC().getX() -
                        triangle.getA().getX() * triangle.getA().getX() +
                        triangle.getC().getY() * triangle.getC().getY() -
                        triangle.getA().getY() * triangle.getA().getY()) * (triangle.getB().getY() - triangle.getA().getY())
        ) / ((triangle.getB().getX() - triangle.getA().getX()) * (triangle.getC().getY() - triangle.getA().getY()) -
                (triangle.getC().getX() - triangle.getA().getX()) * (triangle.getB().getY() - triangle.getA().getY()));

        tempCY = 0.5 * ((triangle.getC().getX() * triangle.getC().getX() -
                triangle.getA().getX() * triangle.getA().getX() +
                triangle.getC().getY() * triangle.getC().getY() -
                triangle.getA().getY() * triangle.getA().getY()) * (triangle.getB().getX() - triangle.getA().getX()) -
                (triangle.getB().getX() * triangle.getB().getX() -
                        triangle.getA().getX() * triangle.getA().getX() +
                        triangle.getB().getY() * triangle.getB().getY() -
                        triangle.getA().getY() * triangle.getA().getY()) * (triangle.getC().getX() - triangle.getA().getX())
        ) / ((triangle.getB().getX() - triangle.getA().getX()) * (triangle.getC().getY() - triangle.getA().getY()) -
                (triangle.getC().getX() - triangle.getA().getX()) * (triangle.getB().getY() - triangle.getA().getY()));
        //вычислим радиус
        tempR = Math.sqrt((tempCX - triangle.getA().getX()) * (tempCX - triangle.getA().getX()) +
                (tempCY - triangle.getA().getY()) * (tempCY - triangle.getA().getY()));
        //проверяем все точки в коллекции на предмет попадания в треугольник
        for (int i = 0; i < pointCollection.size(); i++) {
            if (!pointCollection.get(i).equals(triangle.getA()) && !pointCollection.get(i).equals(triangle.getB()) && !pointCollection.get(i).equals(triangle.getC())) {
                if (Math.sqrt((pointCollection.get(i).getX() - tempCX) * (pointCollection.get(i).getX() - tempCX) + (pointCollection.get(i).getY() - tempCY) * (pointCollection.get(i).getY() - tempCY)) < tempR) {
                    check = false;
                }
            }
        }

        if (check) {
            Circle temp = new Circle();
            temp.setCenterX(tempCX);
            temp.setCenterY(tempCY);
            temp.setRadius(tempR);
            circleCollection.add(temp);
            triangleCollection.add(triangle);
        }
    }
}
