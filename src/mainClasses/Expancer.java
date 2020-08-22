package mainClasses;

import helperClasses.BinaryTree;
import helperClasses.Node;
import helperClasses.NodeLinkedList;
import helperClasses.PriorityQueue;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;

public class Expancer {
    File mainFile;

    public Expancer(File file) {
        mainFile = file;
    }

    public File expance() {
        int pointer = findPointer();
        NodeLinkedList encodings = new NodeLinkedList();
        findEncodings(encodings);

        try {
//            for(int i=0 ; i<encodings.getSize() ; i++) {
//                System.out.println(encodings.get(i));
//            }

            BufferedReader reader = new BufferedReader(new FileReader(mainFile));
            String line = reader.readLine();


            while (!line.trim().equals("finishfinishfinish")) {
                line = reader.readLine();
            }
            line = reader.readLine();


            File temp = new File(mainFile.getAbsolutePath().substring(0, mainFile.getAbsolutePath().length() - mainFile.getName().length()) + "temp.txt");
            if (temp.createNewFile()) {
                System.out.println("temp successfully created");
            }
            else {
                temp.delete();
                temp.createNewFile();
            }



            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            while(line != null){
                writer.append(line+"\n");
                line = reader.readLine();
            }
            reader.close();
            writer.close();


            File eResult = new File(mainFile.getAbsolutePath().substring(0, mainFile.getAbsolutePath().length() - mainFile.getName().length()) + "ExpanceResult.txt");
            if (eResult.createNewFile()) {
                System.out.println("ExpanResultFile successfully created");
            }
            else {
                eResult.delete();
                eResult.createNewFile();
            }


            byte[] bytes = Files.readAllBytes(temp.toPath());
            String stringBytes = "";

            for (int i=0 ; i<bytes.length ;i++) {
                if((bytes[i] & 64) == 64) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 2)
                    break;
                if((bytes[i] & 32) == 32) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 3)
                    break;
                if((bytes[i] & 16) == 16) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 4)
                    break;
                if((bytes[i] & 8) == 8) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 5)
                    break;
                if((bytes[i] & 4) == 4) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 6)
                    break;
                if((bytes[i] & 2) == 2) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                if(i == bytes.length-1 && pointer < 7)
                    break;
                if((bytes[i] & 1) == 1) {
                    stringBytes = stringBytes + "1";
                    stringBytes = check(stringBytes, encodings, eResult);
                }
                else {
                    stringBytes = stringBytes + "0";
                    stringBytes = check(stringBytes, encodings, eResult);
                }

            }

            temp.delete();
            return eResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //unreachable statement
        return mainFile;
    }

    private String check(String stringBytes, NodeLinkedList encodings, File resultFile) {
        String key = encodings.getKey(stringBytes);
        if(key != null) {

            stringBytes = "";
            try {


                FileOutputStream out = new FileOutputStream(resultFile, true);
                out.write(key.getBytes());
                out.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBytes;
    }


    private void findEncodings(NodeLinkedList encodings) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(mainFile));
            String line = reader.readLine();
            line = reader.readLine();

            boolean enterPassed =false;
            while(!line.trim().equals("finishfinishfinish")) {
                if(line.trim().equals("") && !enterPassed) {

                    enterPassed = true;
                    encodings.addFirst(new Node("\n", (line = reader.readLine()).trim().split("-")[1]));

                }else if(line.trim().equals("") && enterPassed) {
                    encodings.addFirst(new Node("", (line = reader.readLine()).trim().split("-")[1]));
                } else {
                    encodings.addFirst(new Node(line.split("-")[0], line.split("-")[1].trim()));
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findPointer() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(mainFile));
            String line = reader.readLine();
            reader.close();
            return Integer.valueOf(line.trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        File file = new File("C:\\Users\\arvin\\Desktop\\resultFile.txt");
//        new Expancer(file).expance();
//    }
}
