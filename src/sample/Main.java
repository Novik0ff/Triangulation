package sample;

import Calculate.Triangle;
import Calculate.Triangulation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group image = new Group();
        Group control = new Group();
        Group circlesGroup = new Group();
        Button btn = new Button("Click");
        TextField textField = new TextField();
        textField.setText("10");
        Button btnCircle = new Button("Circles");
        ArrayList<Circle> circleArrayList = new ArrayList<>();
        GridPane grid = new GridPane();
        grid.add(btn, 0, 0);
        grid.add(textField, 0, 1);
        grid.add(btnCircle, 0, 2);
        grid.setHgap(10);
        grid.setVgap(10);

        // событие клика по кнопке Circles
        btnCircle.setOnAction(event -> {
            if (circlesGroup.getChildren().size() > 0) { // если есть круги, то очищаем коллекцию
                circlesGroup.getChildren().clear();
            } else {
                //добавляем круги для отрисовки
                for (int i = 0; i < circleArrayList.size(); i++) {
                    Circle circle = new Circle();
                    circle.setRadius(circleArrayList.get(i).getRadius());
                    circle.setCenterY(circleArrayList.get(i).getCenterY());
                    circle.setCenterX(circleArrayList.get(i).getCenterX());
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStroke(Color.BLUE);
                    circlesGroup.getChildren().add(circle);
                }
            }
        });
        btn.setOnAction(event -> {
                    //  очищаем группу
                    control.getChildren().clear();
                    circlesGroup.getChildren().clear();
                    try {
                        // парсим число точек на плоскости
                        int size = Integer.parseInt(textField.getText());
                        //задаем триангуляцию
                        Triangulation triangulation = new Triangulation(size, 0, 1200, 0, 800);

                        //добавляем в группу треугольники
                        for (Triangle triangle : triangulation.triangleCollection) {
                            drawTriangle(triangle, control);
                        }
                        //добавляем в друппу точки на плостости
                        for (int i = 0; i < triangulation.pointCollection.size(); i++) {

                            Ellipse tempEclipse = new Ellipse();
                            tempEclipse.setCenterX(triangulation.pointCollection.get(i).getX());
                            tempEclipse.setCenterY(triangulation.pointCollection.get(i).getY());
                            tempEclipse.setRadiusX(5);
                            tempEclipse.setRadiusY(5);
                            tempEclipse.setFill(Color.RED);
                            control.getChildren().add(tempEclipse);
                        }
                        circleArrayList.clear();
                        for (int i = 0; i < triangulation.circleCollection.size(); i++) {
                            circleArrayList.add(triangulation.circleCollection.get(i));
                        }
                        control.getChildren().add(circlesGroup);

                    } catch (Exception ex) {
                        // выдаем сообщение об ошибке
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Введите целое число");
                        alert.showAndWait();
                    }
                }
        );
        //добавляем нашу группу для отрисовки
        image.getChildren().add(control);
        image.getChildren().add(grid);
        primaryStage.getIcons().add(new Image("rofl.jpg"));
        primaryStage.setTitle("Triangulation");
        primaryStage.setScene(new Scene(image, 1200, 800));
        primaryStage.show();
    }

    public static void drawTriangle(Triangle triangle, Group group) {
        Line line1 = new Line();
        line1.setStartX(triangle.getA().getX());
        line1.setStartY(triangle.getA().getY());
        line1.setEndX(triangle.getB().getX());
        line1.setEndY(triangle.getB().getY());
        Color dsd = new Color(Math.abs(new Random().nextDouble() % 1), new Random().nextDouble() % 1, new Random().nextDouble() % 1, 1);
        line1.setStroke(dsd);
        group.getChildren().add(line1);

        Line line2 = new Line();
        line2.setStartX(triangle.getA().getX());
        line2.setStartY(triangle.getA().getY());
        line2.setEndX(triangle.getC().getX());
        line2.setEndY(triangle.getC().getY());
        line2.setStroke(dsd);
        group.getChildren().add(line2);

        Line line3 = new Line();
        line3.setStartX(triangle.getB().getX());
        line3.setStartY(triangle.getB().getY());
        line3.setEndX(triangle.getC().getX());
        line3.setEndY(triangle.getC().getY());
        line3.setStroke(dsd);
        group.getChildren().add(line3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
