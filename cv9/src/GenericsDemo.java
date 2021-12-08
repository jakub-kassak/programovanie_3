import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {
    static void drawAll1(List<Shape> list){
        list.forEach(Shape::draw);
        list.add(new Circle());
    }

    static void drawAll2(List<? extends Shape> list){
        list.forEach(Shape::draw);
        //list.add(new Circle())
    }

    static void drawAll3(List<?> list){
        list.forEach(Object::toString);
        //list.forEach(Shape::draw);
        //list.add(new Circle());
    }

    static void drawAll4(List<? super Shape> list){
        //list.forEach(Shape::draw);
        list.add(new Circle());
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle c = new Circle();
            shapes.add(c);
            circles.add(c);
        }
        drawAll1(shapes);
        //drawAll1(circles);
        drawAll2(circles);
        drawAll2(shapes);
        drawAll3(shapes);
        drawAll3(circles);
        //drawAll4(circles);
        drawAll4(shapes);
    }
}
