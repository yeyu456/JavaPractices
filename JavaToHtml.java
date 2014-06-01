import java.util.Arrays;
import java.util.HashSet;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class JavaToHtml {
    public static void main(String[] args){
        JTH test = new JTH();
        int count = 1;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            FileWriter fw = new FileWriter(args[1]);
            fw.write(test.htmlHeader);
            fw.flush();
            String line = br.readLine();
            while(line!=null){
                fw.write(test.translate(line));
                fw.flush();
                line = br.readLine();
                count++;
            }
            fw.write(test.htmlTailer);
            fw.flush();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class JTH {
    final String htmlHeader = "<html>\n<head><title>Java File</title></head>\n<body>\n";
    final String htmlTailer = "</body>\n</html>";
    private final String rowHeader = "<p>";
    private final String rowTailer = "</p>\n";
    private final String typeForKeyWord = "<b><font color=\"#000079\">";
    private final String typeForComment = "<b><font color=\"#28FF28\">";
    private final String typeForData = "<b><font color=\"#0066CC\">";
    private final String typeTailer = "</font></b>";
    
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
    boolean isString = false;
    
    public String translate (String line){
        boolean isComment = false;
        String newLine = rowHeader;
        String temp = "";
        line = line.replaceAll("\t", "\\s{4}").replaceAll("\\s", "&nbsp;");
        String[] word = line.split("&nbsp;");
        for(String s:word){
            s = s.replace(">", "&gt;");
            s = s.replace("<", "&lt;");
            if(!isComment){
                if(s.contains("//")){
                    isComment = true;
                    temp = s.replaceFirst("//", typeForComment + "//");
                }
                else
                    if(s.contains("\"")){
                        temp = this.stringCheck(s);
                    }
                    else{
                        temp = this.keywordCheck(s);
                        temp = this.dataCheck(temp);
                    }
            }
            else{
                if(isComment)
                    temp = s;
            }
            newLine += "&nbsp;" + temp;
        }
        if(isComment){
            newLine += "&nbsp;" + typeTailer;
            isComment = false;
        }
        newLine += rowTailer;
        return newLine;
    }
    
    private String keywordCheck(String s){
        for(String n:s.split("[\\(\\)\\{\\}\\[\\]=<>]")){
            if(kw.contains(n))
                s = s.replace(n, typeForKeyWord + n + typeTailer);
        }
        return s;
    }
    
    private String dataCheck(String s){
        int index = 0;
        for(String n:s.split("[\\(\\)\\{\\}\\[\\]=><\\+\\-\\*%\\/;,]")){
            if(n.matches("\\d+|null")){
                s = s.substring(0, index) 
                    + typeForData + n 
                    + typeTailer 
                    + s.substring(index+n.length());
                index += typeForData.length() + typeTailer.length();
            }
            index += n.length() + 1;
        }
        return s;
    }
    
    public String stringCheck(String s){
        for(String n:s.split("[\\(\\)\\{\\}\\[\\]=\\+;,]")){
            if(n.matches("\\\".*\\\""))
                s = s.replace(n, typeForData + n + typeTailer);
            else{
                if(n.contains("\"")){
                    if(isString){
                        isString = false;
                        s = s.replace("\"", "\"" + typeTailer);
                    }
                    else{
                        isString = true;
                        s = s.replaceFirst("\"", typeForData + "\"");
                    }
                }
            }
        }
        return s;
    }
}
