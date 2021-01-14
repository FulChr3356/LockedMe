package io.fulchr3356.lockedme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class FileManagement  {
    Map<String, Document> files = new TreeMap<String,Document>();

    FileManagement() {
        try (Stream<Path> paths = Files.walk(Paths.get("files/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            files.put(file.toFile().getName(),new Document(file.toFile()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printAllFiles() {
        files.forEach((fileName, file) -> System.out.println(fileName));
    }

    public void addFile(String fileName,Document file) {
        if (files.put(fileName,file) == null)
            System.out.println("File added successfully. File name: " + file.getFile().getName());
        else
            System.out.println("File add unsuccessful :(");

    }

    public void delete(String fileName) {
        Document currFile = files.get(fileName);
        if (files.remove(fileName) != null && currFile.getFile().delete())
            System.out.println("File deletion successful");
        else
            System.out.println("File deletion unsuccessful");
    }

    public Document search(String fileName) {
        return files.get(fileName);
    }

    public void updateFile(String name, Document updatedFile) {
        files.put(name,updatedFile);
    }
}
