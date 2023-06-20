public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        IoHandler ioHandler = new IoHandler(data);
        RectangleMethod rectangleMethod = new RectangleMethod(data);
        try {
            data.isFunctionDefined();
            System.out.println(rectangleMethod.doMethod());
        } catch (FunctionNotDefinedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}