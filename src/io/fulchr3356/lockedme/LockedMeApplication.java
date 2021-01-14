package io.fulchr3356.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LockedMeApplication {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input;
        FileManagement fileManagement = new FileManagement();
        Document currentFile;
        File tempFile;
        System.out.println("$$\\                          $$\\                       $$\\ $$\\      $$\\           \n" +
                "$$ |                         $$ |                      $$ |$$$\\    $$$ |          \n" +
                "$$ |      $$$$$$\\   $$$$$$$\\ $$ |  $$\\  $$$$$$\\   $$$$$$$ |$$$$\\  $$$$ | $$$$$$\\  \n" +
                "$$ |     $$  __$$\\ $$  _____|$$ | $$  |$$  __$$\\ $$  __$$ |$$\\$$\\$$ $$ |$$  __$$\\ \n" +
                "$$ |     $$ /  $$ |$$ /      $$$$$$  / $$$$$$$$ |$$ /  $$ |$$ \\$$$  $$ |$$$$$$$$ |\n" +
                "$$ |     $$ |  $$ |$$ |      $$  _$$<  $$   ____|$$ |  $$ |$$ |\\$  /$$ |$$   ____|\n" +
                "$$$$$$$$\\\\$$$$$$  |\\$$$$$$$\\ $$ | \\$$\\ \\$$$$$$$\\ \\$$$$$$$ |$$ | \\_/ $$ |\\$$$$$$$\\ \n" +
                "\\________|\\______/  \\_______|\\__|  \\__| \\_______| \\_______|\\__|     \\__| \\_______|\nWritten by Christopher Fulton - HCL");
        label:
        while(true) {
            System.out.println("Enter 0 to exit program\n" +
                    "Enter 1 to retrieve all file names \n" +
                    "Enter 2 to create new file\n" +
                    "Enter 3 to add file from disk\n" +
                    "Enter 4 to delete file from application\n" +
                    "Enter 5 to search for file");
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    break label;
                case "1":
                    fileManagement.printAllFiles();
                    break;
                case "2":
                    System.out.println("Enter file name:");
                    String fileName = scanner.nextLine();
                    fileManagement.addFile(fileName, new Document(fileName));
                    break;
                case "3":
                    System.out.println("Enter absolute path of file:");
                    try{
                        tempFile = new File(scanner.nextLine());
                        currentFile = new Document(tempFile,tempFile.getName());
                        fileManagement.addFile(currentFile.getFile().getName(),currentFile);
                    }catch(Exception e){
                        System.out.println("Error in copying file!!! :(");
                    }

                    break;
                case "4":
                    System.out.println("Enter file name to delete:");
                    fileManagement.delete(scanner.nextLine());
                    break;
                case "5":
                    System.out.println("Enter file name:");
                    currentFile = fileManagement.search(scanner.nextLine());
                    if (currentFile == null ){
                        System.out.println("File Not Found!!");
                        break;
                    }

                    else
                        fileHandler(fileManagement,currentFile);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }


    }
    static void fileHandler(FileManagement manager, Document file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("What would you like to do with the file?");
        label:
        while(true) {
            System.out.println("Enter 0 return to main menu\nEnter 1 to read file contents\nEnter 2 to append to file\nEnter 3 to overwrite file contents");
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    break label;
                case "1":
                    System.out.println(file.read());
                    break;
                case "2":
                    System.out.println("Enter text to append to file:");
                    file.write(scanner.nextLine());
                    manager.updateFile(file.getFile().getName(),file);
                    break;
                case "3":
                    System.out.println("Enter text to overwrite file with **WARNING** this will overwrite file contents");
                    file.overWrite(scanner.nextLine());
                    manager.updateFile(file.getFile().getName(),file);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }
}
