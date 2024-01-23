package com.iuh.fit.zian;

import com.github.javaparser.StaticJavaParser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File projectDir = new File("E:\\Document-IUH\\Documents\\HK8\\KIENTRUCPHANMEM\\submit\\Tuan2\\TestTemplate");
        Dictionary dictionary = new Dictionary();
        List<String> requiredComments = List.of("@author", "created-date");
        String packagePattern = "com.iuh";


        DirExplorer dirExplorer = new DirExplorer(
                ((level, path, file) -> path.endsWith(".java")),
                (level, path, file) -> {
                    System.out.println(path);
                    System.out.println("-".repeat(path.length()));

                    try {
                        VoidVisitorAdapterCustom voidVisitorAdapterCustom = new VoidVisitorAdapterCustom();
                        voidVisitorAdapterCustom.setPatternPackage(packagePattern);
                        voidVisitorAdapterCustom.setRequiredComments(requiredComments);
                        voidVisitorAdapterCustom.setDictionary(dictionary);
                        voidVisitorAdapterCustom.visit(StaticJavaParser.parse(file), null);
                        System.out.println();
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                });
        dirExplorer.explore(projectDir);

//        Test
//        Dictionary dictionary = new Dictionary();
//        System.out.println(dictionary.isNoun("requiredComments"));
//        System.out.println(Utils.splipByUpperCase("BuiNhutDuy"));

//        String comment = "/**\n" +
//                " * @author BuiNhutDuy\n" +
//                " * created-date 2024\n" +
//                " */";
//        System.out.println(!comment.contains("@author"));
    }
}