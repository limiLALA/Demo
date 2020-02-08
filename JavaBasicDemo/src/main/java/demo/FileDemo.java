package demo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDemo {

    static class DirectoryDemo{

        static void run(){
            File current = new File(".");
            String absolutePath = current.getAbsolutePath();
            System.out.println(absolutePath);

            Path path = Paths.get("root").toAbsolutePath();
            System.out.println(path.toAbsolutePath());
            path = path.resolve("take");
            System.out.println(path);
        }

    }

    public static void main(String[] args) {
        DirectoryDemo.run();
    }

}
