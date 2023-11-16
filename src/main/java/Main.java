import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer("Hello Java World");
        Class tcClass = textContainer.getClass();
        Annotation pathAnnotation = tcClass.getAnnotation(SaveTo.class);
        Method[] methods = tcClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(textContainer, getPathFromAnnotation(pathAnnotation.toString()));
                } catch (IllegalAccessException e) {

                } catch (InvocationTargetException e) {

                }
            }
        }
    }

    private static String getPathFromAnnotation(String line) {
        int openingParenthesisIndex = line.indexOf("(");
        int closingParenthesisIndex = line.indexOf(")");
        String[] param = line.substring(openingParenthesisIndex + 1, closingParenthesisIndex).split("=");
        return param[1].substring(1,param[1].length()-1);
    }
}
