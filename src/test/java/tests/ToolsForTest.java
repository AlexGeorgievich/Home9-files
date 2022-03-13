package tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static tests.OpenZipAndTest.pathResources;
import static tests.OpenZipAndTest.tempDir;

public class ToolsForTest {
//public String tempDir;
//    разархивирование  zip файла в каталог tempDir в среде IDEA для размещения группы
//    временных файлов на период тестирования  - удалить  -> gradle clear
 public static void unZip( String nameZipFile) throws Exception{
//        String tempDir = "src/test/resources/files/";
        String zipFile = pathResources + nameZipFile;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                System.out.printf("File name: %s \n", name);  //  контроль процесса извлечения файлов
                // распаковка
                FileOutputStream fout = new FileOutputStream(tempDir + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
    }
//    creat new directory for some experiments with files systems
//    This part added just for some auditor by nik name "Alex"
    public static  String creatTempDir() throws Exception {
        String addDirTemp = "build\\temp\\";
        Path str = Paths.get(pathResources);
        String pathAbs = String.valueOf(str.toAbsolutePath());
        String tempDir1 = pathAbs.substring(0,pathAbs.indexOf("src")) + addDirTemp ;
        Path pathTempDir = Paths.get(tempDir1);
        if (Files.exists(pathTempDir)) {
            System.out.println(addDirTemp + " - directory  exists");
        } else{
            Path donePath = Files.createDirectory(pathTempDir);
        }
        return tempDir1;
    }
}
