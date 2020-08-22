package mainClasses;

import java.io.File;

public class MainRunner {

    //MainClass Of proj
    public static void main(String[] args) {

        //use Comperessor.compress() method to Compress mainFile
        File mainFile = new File("mainFile.txt");

        //encodResult is the encoded File
        File encodReasult = new Compressor(mainFile).compress();

        //decodResult is the decoded File
        File decodReasult = new Expancer(mainFile).expance();

    }
}
