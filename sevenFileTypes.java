import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class sevenFileTypes {
    public static void converter(String fileName, String targetFolder) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean condition1Met = false;
            boolean condition2Met = false;
            boolean insideBlock = false;
            StringBuilder arrSB[] = new StringBuilder[7];
            for (int i = 0; i < arrSB.length; i++) {
                arrSB[i] = new StringBuilder("");
            }
            // int totalLines = 0;
            int moCallRecordLines = 0;
            int mtCallRecordLines = 0;
            int moSMSRecordLines = 0;
            int mtSMSRecordLines = 0;
            int ssActionRecordLines = 0;
            int transitRecordLines = 0;
            int forwardCallRecordLines = 0;
            int curIndex = -1;
            File folder = new File(targetFolder, fileName);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Folder created successfully.");
                } else {
                    System.err.println("Failed to create the folder.");
                }
            } else {
                System.out.println("The folder already exists.");
            }
            while ((line = reader.readLine()) != null) {
                // Check condition 1 on the first line
                if (line.contains("callEventDataFile")) {
                    condition1Met = true;
                    insideBlock = false;
                    if (moCallRecordLines == 2) {
                        moCallRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "moCallRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[0].deleteCharAt(arrSB[0].length() - 1);
                        arrSB[0].append("\n");
                        writer.append(arrSB[0].toString());
                        writer.close();
                        arrSB[0].setLength(0);
                    } else if (mtCallRecordLines == 2) {
                        mtCallRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "mtCallRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[1].deleteCharAt(arrSB[1].length() - 1);
                        arrSB[1].append("\n");
                        writer.append(arrSB[1].toString());
                        writer.close();
                        arrSB[1].setLength(0);
                    } else if (moSMSRecordLines == 2) {
                        moSMSRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "moSMSRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[2].deleteCharAt(arrSB[2].length() - 1);
                        arrSB[2].append("\n");
                        writer.append(arrSB[2].toString());
                        writer.close();
                        arrSB[2].setLength(0);
                    } else if (mtSMSRecordLines == 2) {
                        mtSMSRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "mtSMSRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[3].deleteCharAt(arrSB[3].length() - 1);
                        arrSB[3].append("\n");
                        writer.append(arrSB[3].toString());
                        writer.close();
                        arrSB[3].setLength(0);
                    } else if (ssActionRecordLines == 2) {
                        ssActionRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "ssActionRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[4].deleteCharAt(arrSB[4].length() - 1);
                        arrSB[4].append("\n");
                        writer.append(arrSB[4].toString());
                        writer.close();
                        arrSB[4].setLength(0);
                    } else if (transitRecordLines == 2) {
                        transitRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "transitRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[5].deleteCharAt(arrSB[5].length() - 1);
                        arrSB[5].append("\n");
                        writer.append(arrSB[5].toString());
                        writer.close();
                        arrSB[5].setLength(0);
                    } else if (forwardCallRecordLines == 2) {
                        forwardCallRecordLines = 0;
                        String outputFile = folder.getAbsolutePath() + "/" + "forwardCallRecord.csv";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                        arrSB[6].deleteCharAt(arrSB[6].length() - 1);
                        arrSB[6].append("\n");
                        writer.append(arrSB[6].toString());
                        writer.close();
                        arrSB[6].setLength(0);
                    }
                    continue;
                }
                if ((line.contains("moCallRecord") || line.contains("mtCallRecord") || line.contains("moSMSRecord")
                        || line.contains("mtSMSRecord") || line.contains("ssActionRecord")
                        || line.contains("transitRecord") || line.contains("forwardCallRecord"))
                        && condition1Met == true) {
                    condition2Met = true;
                    if (line.contains("moCallRecord")) {
                        curIndex = 0; // i is 0 for mtSMSRecord;
                    } else if (line.contains("mtCallRecord")) {
                        curIndex = 1;
                    } else if (line.contains("moSMSRecord")) {
                        curIndex = 2;
                    } else if (line.contains("mtSMSRecord")) {
                        curIndex = 3;
                    } else if (line.contains("ssActionRecord")) {
                        curIndex = 4;
                    } else if (line.contains("transitRecord")) {
                        curIndex = 5;
                    } else if (line.contains("forwardCallRecord")) {
                        curIndex = 6;
                    }
                } else if ((line.contains("moCallRecord") || line.contains("mtCallRecord")
                        || line.contains("moSMSRecord")
                        || line.contains("mtSMSRecord") || line.contains("ssActionRecord")
                        || line.contains("transitRecord") || line.contains("forwardCallRecord"))
                        && condition1Met == false) {
                    insideBlock = false;
                }
                if (condition1Met && condition2Met) {
                    insideBlock = true;
                    if (curIndex == 0) {
                        moCallRecordLines++;
                    } else if (curIndex == 1) {
                        mtCallRecordLines++;
                    } else if (curIndex == 2) {
                        moSMSRecordLines++;
                    } else if (curIndex == 3) {
                        mtSMSRecordLines++;
                    } else if (curIndex == 4) {
                        ssActionRecordLines++;
                    } else if (curIndex == 5) {
                        transitRecordLines++;
                    } else if (curIndex == 6) {
                        forwardCallRecordLines++;
                    }
                    condition1Met = false;
                    condition2Met = false;
                    for (int i = 0; i < arrSB.length; i++) {
                        if (arrSB[i].length() > 0) {
                            arrSB[i].deleteCharAt(arrSB[i].length() - 1);
                            arrSB[i].append("\n");
                        }
                    }
                    continue;
                }
                // After both conditions are met, further processing logic can be applied next.
                if (insideBlock) {
                    // check if line is empty
                    if (line.length() == 0) {
                        continue;
                    }
                    // variable to track each line character by character
                    int i = 0;
                    i = line.indexOf('=');
                    if (i != -1) {
                        line = line.substring(i + 1);
                        line = line.trim();
                        boolean executed = false; // to know if ',' needs to be added or not
                        for (int j = 0; j < line.length(); j++) {
                            arrSB[curIndex].append(line.charAt(j));
                            executed = true;
                        }
                        if (executed == true) {
                            // new string found so add a ',' character
                            arrSB[curIndex].append(",");
                        }
                    }
                }
            }
            for (int i = 0; i < arrSB.length; i++) {
                if (arrSB[i].length() > 0) {
                    arrSB[i].deleteCharAt(arrSB[i].length() - 1);
                }
            }
            reader.close();
            // int forwardCallRecordLines = 0;
            if (moCallRecordLines != 0) {
                moCallRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "moCallRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[0].toString());
                writer.close();
                arrSB[0].setLength(0);
            }
            if (mtCallRecordLines != 0) {
                mtCallRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "mtCallRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[1].toString());
                writer.close();
                arrSB[1].setLength(0);
            }
            if (moSMSRecordLines != 0) {
                moSMSRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "moSMSRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[2].toString());
                writer.close();
                arrSB[2].setLength(0);
            }
            if (mtSMSRecordLines != 0) {
                mtSMSRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "mtSMSRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[3].toString());
                writer.close();
                arrSB[3].setLength(0);
            }
            if (ssActionRecordLines != 0) {
                ssActionRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "ssActionRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[4].toString());
                writer.close();
                arrSB[4].setLength(0);
            }
            if (transitRecordLines != 0) {
                transitRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "transitRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[5].toString());
                writer.close();
                arrSB[5].setLength(0);
            }
            if (forwardCallRecordLines != 0) {
                forwardCallRecordLines = 0;
                String outputFile = folder.getAbsolutePath() + "/" + "forwardCallRecord.csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(arrSB[6].toString());
                writer.close();
                arrSB[6].setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        converter("something.txt", "F:/GTSTECHLABS/somefolder");
    }
}
