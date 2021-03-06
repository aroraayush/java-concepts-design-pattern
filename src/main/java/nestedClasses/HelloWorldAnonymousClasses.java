package nestedClasses;

import java.util.ArrayList;

/**
 * @author https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
 * A class that shows how to use local classes and anonymous classes.
 *
 */

public class HelloWorldAnonymousClasses {

    private String data = "!!!";

    interface HelloWorld {
        public void greet();

        public void greetSomeone(String someone);
    }

    public void sayHello() {

        // Local Class
        class EnglishGreeting implements HelloWorld {
            String name = "world";

            public void greet() {
                greetSomeone("world" + data);
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        // Local Class instance - can be used for several objects
        HelloWorld englishGreeting = new EnglishGreeting();

        // Using an anonymous class - can be used for only 1 object
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde"; // means "everybody"

            public void greet() {
                greetSomeone("tout le monde");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name + data);
            }
        };

        // Using an anonymous class
        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";

            public void greet() {
                greetSomeone("mundo");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };

        ArrayList<HelloWorld> greetings = new ArrayList();
        greetings.add(englishGreeting);
        greetings.add(spanishGreeting);
        greetings.add(frenchGreeting);

        for (HelloWorld gr: greetings) {
            gr.greet();
        }
    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }
}