import java.util.Arrays;
import java.util.HashSet;
import java.io.FileReader;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class JavaToHtml {
    public static void main(String[] args){
        JTH test = new JTH();
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            FileWriter fw = new FileWriter(args[1]);
            String line = br.readLine();
            while(line!=null){
                fw.write(test.translate(line));
                fw.flush();
                line = br.readLine();
            }
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class JTH {
    private final String htmlHeader = "<html>\n<head><title>Java File</title></head>\n<body>\n";
    private final String htmlTailer = "</body>\n</html>";
    private final String rowHeader = "<p>";
    private final String rowTailer = "</p>\n";
    private final String typeForKeyWord = "<b><font color=\"#000079\">";
    private final String typeForComment = "<b><font color=\"#28FF28\">";
    private final String typeForData = "<b><font color=\"#0066CC\">";
    private final String typeTailer = "</font></b>\n";
    
    private final String[] keywords = {"abstract", "boolean", "break",
                                       "byte", "case", "catch", "char",
                                       "class", "continue", "do", "double",
                                       "else", "extends", "final", "finally",
                                       "float", "for", "if", "implements",
                                       "import", "instanceof", "int", "interface",
                                       "long", "new", "package", "private", 
                                       "protected", "public", "return", "short",
                                       "static", "super", "switch", "this", 
                                       "throw", "throws", "try", "void", "while"}; 
    private final HashSet<String> kw = new HashSet<String>(Arrays.asList(keywords));
    
    public String translate (String line){
        boolean isComment = false;
        boolean isString = false;
        String newLine = rowHeader;
        String temp = "";
        line = line.replaceAll("\t", "\\s{4}");
        String[] word = line.split("\\s");
        for(String s:word){
            if(!isComment&&!isString){
                if(s=="//"||s.matches("//")){
                    isComment = true;
                    newLine += typeForComment + s;
                    continue;
                }
                else
                    if(s.matches("\"")){
                        isString = true;
                        temp += typeForData + s;
                    }
                    else{
                        temp = this.keywordCheck(s);
                        temp = this.dataCheck(s);
                    }
            }
            else{
                if(isString&&s.matches("\"")){
                    isString = false;
                    temp += s + typeTailer;
                }
            }
            newLine += temp;
        }
        if(isComment)
            newLine += typeTailer;
        return newLine;
    }
    
    private String keywordCheck(String s){
        String tmp = "";
        for(String n:s.split("[(){}]="))
            if(kw.contains(n))
                tmp += typeForKeyWord + n + typeTailer;
        return tmp;
    }
    
    private String dataCheck(String s){
        if(s.matches("(=*)(\\d+|null)")){
            s = s.replaceAll("(=*)(\\d+|null);", 
                    typeForData + "\1\2" + typeTailer);
        }
        return s;
    }
}
