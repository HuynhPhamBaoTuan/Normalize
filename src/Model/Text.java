package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Text {

    //Khoảng trắng giữa kí tự đb
    public String formatOneSpaceSpecial(String line, String character) {
        StringBuilder stringBuffer = new StringBuilder();
        String[] strings = line.split("\\s*\\" + character + "\\s*");
        //appen every word and character special distance is one space
        for (String oneWord : strings) {
            stringBuffer.append(oneWord).append(" ").append(character);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim().substring(0, stringBuffer.length() - 3);
    }

    //chỉ có 1 khoảng trắng giữa mọi từ
    public String formatOneSpace(String line) {
        line = line.toLowerCase();
        line = line.replaceAll("\\s+", " ");
        line = formatOneSpaceSpecial(line, ".");
        line = formatOneSpaceSpecial(line, ",");
        line = formatOneSpaceSpecial(line, ":");
        line = formatOneSpaceSpecial(line, "\"");
        return line.trim();
    }

    //Không có hai dấu . dấu , dấu : ; liên tiếp 
    public String formatSpecialCharacters(String line) {
        StringBuilder stringBuffer = new StringBuilder(line);
        //kiểm tra từ đầu đến cuối .,:; sau đó xóa
        for (int i = 0; i < stringBuffer.length() - 1; i++) {
            if (stringBuffer.charAt(i) == ' '
                    && stringBuffer.charAt(i + 1) == '.'
                    || stringBuffer.charAt(i + 1) == ','
                    || stringBuffer.charAt(i + 1) == ':') {
                stringBuffer.deleteCharAt(i);
            }
        }
        return stringBuffer.toString().trim();
    }

    //Sau dấu chấm GHI HOA, còn lại ghi thường
    public String afterDotUpperCase(String line) {
        StringBuilder stringBuffer = new StringBuilder(line);
        int lengthLine = stringBuffer.length();
        //kiểm tra từ đầu đến cuối sau dấu "." => ghi hoa
        for (int i = 0; i < lengthLine - 2; i++) {
            if (stringBuffer.charAt(i) == '.') {
                char afterDot = stringBuffer.charAt(i + 2);
                stringBuffer.setCharAt(i + 2, Character.toUpperCase(afterDot));
            }
        }
        return stringBuffer.toString().trim();
    }

    //Xóa đi khoảng trắng trước và sau câu, từ trong dấu(“”)
    static int countQuetes = 0;

    public String noSpaceQuotes(String line) {
        StringBuilder stringBuffer = new StringBuilder(line);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '"' && countQuetes % 2 == 0) {
                stringBuffer.deleteCharAt(i + 1);
                countQuetes++;
            } else if (stringBuffer.charAt(i) == '"' && countQuetes % 2 == 1
                    && i != 0) {
                stringBuffer.deleteCharAt(i - 1);
                countQuetes++;
            }
        }
        return stringBuffer.toString().trim();
    }

    //ký tự đầu tiên của từ trong dòng đầu tiên là chữ hoa
    public String firstUpercase(String line) {
        line = line.substring(3);
        StringBuilder stringBuffer = new StringBuilder(line);
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i))) {
                stringBuffer.setCharAt(i, Character.toUpperCase(line.charAt(i)));
                break;
            }
        }
        return stringBuffer.toString().trim();
    }

    //Dấu chấm cuối đoạn
    public String lastAddDot(String line) {
        if (line.endsWith(".")) {
            return line;
        } else {
            return line + ".";
        }
    }

    //không có dòng trống giữa các dòng
    public boolean isLineEmpty(String line) {
        return line.length() == 0;
    }

    //Đếm dòng
    public int countLine() {
        int countLine = 0;
        try {
            PrintWriter pw;
            try (BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")))) {
                pw = new PrintWriter(new BufferedWriter(new FileWriter("Output.txt", true)));
                String line;
                while ((line = br.readLine()) != null) {
                    if (isLineEmpty(line)) {
                        continue;
                    }
                    countLine++;
                }
            }
            pw.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Can't found.");
        } catch (IOException ex) {
        }
        return countLine;
    }
}