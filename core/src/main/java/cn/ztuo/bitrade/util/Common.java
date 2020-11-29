package cn.ztuo.bitrade.util;

import java.util.regex.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.zip.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import org.apache.commons.lang3.time.*;
import java.text.*;
import java.util.*;

public class Common
{
    private static List<String> filePaths;
    
    public static ArrayList<String> readFileByLine(final String path) {
        return readFileByLine(path, "UTF-8");
    }
    
    public static ArrayList<String> readFileByLine(final String path, final String encoding) {
        final ArrayList<String> result = new ArrayList<String>();
        final File file = new File(path);
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, encoding));
            String line = null;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            closeStream(fis, null, br, null);
        }
        return result;
    }
    
    public static HashSet<String> readFileByLineForSet(final String path) {
        final HashSet<String> result = new HashSet<String>();
        final File file = new File(path);
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("#")) {
                    if (line.equalsIgnoreCase("")) {
                        continue;
                    }
                    result.add(line);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            closeStream(fis, null, br, null);
        }
        return result;
    }
    
    public static void readFileByLineForSet(final String path, final Set<String> result) {
        final File file = new File(path);
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equalsIgnoreCase("")) {
                    continue;
                }
                result.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeStream(fis, null, br, null);
        }
    }
    
    public static HashMap<String, Double> readFileByLineForMap(final String path) {
        final HashMap<String, Double> result = new HashMap<String, Double>();
        final File file = new File(path);
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = null;
            String[] array = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("#")) {
                    if (line.equalsIgnoreCase("")) {
                        continue;
                    }
                    array = line.split("=");
                    if (array == null || array.length != 2) {
                        continue;
                    }
                    result.put(array[0], Double.parseDouble(array[1]));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            closeStream(fis, null, br, null);
        }
        return result;
    }
    
    public static void writeFile(final String filePath, final String content) {
        writeFile(filePath, content, "UTF-8");
    }
    
    public static void writeFile(final String path, final String fileName, final String content, final String encoding) {
        if (path == null || path == null) {
            return;
        }
        final File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        writeFile(path + "/" + fileName, content, encoding);
    }
    
    public static void writeFile(final String filePath, String content, final String encoding) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(filePath, true);
            osw = new OutputStreamWriter(fos, encoding);
            content += "\r\n";
            osw.write(content);
            osw.flush();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            closeStream(null, fos, null, osw);
        }
    }
    
    public static void writeFile(final String filePath, String content, final String encoding, final boolean cover) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(filePath, cover);
            osw = new OutputStreamWriter(fos, encoding);
            content += "\r\n";
            osw.write(content);
            osw.flush();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            closeStream(null, fos, null, osw);
        }
    }
    
    public static void closeStream(final InputStream is, final OutputStream os, final Reader reader, final Writer writer) {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getContent(final String path) {
        if (path == null || "".equals(path)) {
            return null;
        }
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = new FileInputStream(new File(path));
            reader = new BufferedReader(new InputStreamReader(is));
            final StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            closeStream(is, null, reader, null);
        }
        return null;
    }
    
    public static void cleanObj(final Object... objs) {
        for (int i = 0; i < objs.length; ++i) {
            objs[i] = null;
        }
    }
    
    public static boolean isNumeric(final String str) {
        final Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    
    public static String decodeUnicode(final String theString) {
        if (theString == null) {
            return null;
        }
        final int len = theString.length();
        final StringBuffer outBuffer = new StringBuffer(len);
        int x = 0;
        while (x < len) {
            char aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; ++i) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9': {
                                value = (value << 4) + aChar - 48;
                                break;
                            }
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f': {
                                value = (value << 4) + 10 + aChar - 97;
                                break;
                            }
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F': {
                                value = (value << 4) + 10 + aChar - 65;
                                break;
                            }
                            default: {
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                            }
                        }
                    }
                    outBuffer.append((char)value);
                }
                else {
                    if (aChar == 't') {
                        aChar = '\t';
                    }
                    else if (aChar == 'r') {
                        aChar = '\r';
                    }
                    else if (aChar == 'n') {
                        aChar = '\n';
                    }
                    else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            }
            else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
    
    public static boolean deleteFile(final String filePath) {
        try {
            final File file = new File(filePath);
            if (file.exists()) {
                if (file.isDirectory()) {
                    for (final File childFile : file.listFiles()) {
                        deleteFile(childFile.getAbsolutePath());
                    }
                    return file.delete();
                }
                return file.delete();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean existZH(final String str) {
        if (str == null) {
            return false;
        }
        final String regEx = "[\\u4e00-\\u9fa5]";
        final Pattern p = Pattern.compile(regEx);
        final Matcher m = p.matcher(str);
        return m.find();
    }
    
    public static List<String> getObjectFieldNames(final Object object) {
        if (object == null) {
            return null;
        }
        final List<String> fieldNames = new ArrayList<String>();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = object.getClass().getDeclaredFields();
        for (final Field field : declaredFields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
    
    public static Field[] getObjectFields(final Object object) {
        if (object == null) {
            return null;
        }
        final Field[] fields = object.getClass().getDeclaredFields();
        return fields;
    }
    
    public static void moveFile(final String sourcePath, final String targetPath) {
        if (sourcePath == null || targetPath == null) {
            return;
        }
        final File sourcefile = new File(sourcePath);
        if (!sourcefile.exists()) {
            return;
        }
        final File targetPathFile = new File(targetPath);
        if (!targetPathFile.exists()) {
            targetPathFile.mkdirs();
        }
        final String targetFilePath = targetPath + "/" + sourcefile.getName();
        final File targetFile = new File(targetFilePath);
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourcefile));
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
            final byte[] readBytes = new byte[8192];
            int len;
            while ((len = inputStream.read(readBytes)) != -1) {
                outputStream.write(readBytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            deleteFile(sourcePath);
        }
        catch (Exception ex) {}
    }
    
    public static List<String> getFilePaths(final String folder, final String fileName) {
        if (folder == null || fileName == null) {
            return null;
        }
        final File file = new File(folder);
        if (file.exists()) {
            if (file.isDirectory()) {
                final File[] listFiles;
                final File[] childFiles = listFiles = file.listFiles();
                for (final File childFile : listFiles) {
                    getFilePaths(childFile.getAbsolutePath(), fileName);
                }
            }
            else if (file.getName().contains(fileName)) {
                Common.filePaths.add(file.getAbsolutePath());
            }
            return Common.filePaths;
        }
        return null;
    }
    
    public static void zipFile(final String sourceFilePath, final String zipFilePath) {
        if (sourceFilePath == null || zipFilePath == null) {
            return;
        }
        final File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            return;
        }
        try {
            final ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFilePath));
            final BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOutput);
            zip(zipOutput, sourceFile, sourceFile.getName(), bufferedOutput);
            bufferedOutput.close();
            zipOutput.close();
        }
        catch (Exception ex) {}
    }
    
    private static void zip(final ZipOutputStream zipOutput, final File sourceFile, final String base, final BufferedOutputStream bufferedOutput) {
        try {
            if (sourceFile.isDirectory()) {
                final File[] childFiles = sourceFile.listFiles();
                if (childFiles.length == 0) {
                    zipOutput.putNextEntry(new ZipEntry(base + "/"));
                }
                for (int i = 0; i < childFiles.length; ++i) {
                    zip(zipOutput, childFiles[i], base + "/" + childFiles[i].getName(), bufferedOutput);
                }
            }
            else {
                zipOutput.putNextEntry(new ZipEntry(base));
                final FileInputStream FileInput = new FileInputStream(sourceFile);
                final BufferedInputStream bufferedInput = new BufferedInputStream(FileInput);
                int b;
                while ((b = bufferedInput.read()) != -1) {
                    bufferedOutput.write(b);
                }
                bufferedInput.close();
                FileInput.close();
            }
        }
        catch (Exception ex) {}
    }
    
    public static void zipFile(final List<String> sourceFilePaths, final String zipFilePath) {
        if (sourceFilePaths == null || zipFilePath == null) {
            return;
        }
        final List<File> sourceFiles = new ArrayList<File>();
        for (final String sourceFilePath : sourceFilePaths) {
            final File sourceFile = new File(sourceFilePath);
            if (sourceFile.exists()) {
                sourceFiles.add(sourceFile);
            }
        }
        try {
            final ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFilePath));
            final BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOutput);
            zip(zipOutput, sourceFiles, bufferedOutput);
            bufferedOutput.close();
            zipOutput.close();
        }
        catch (Exception ex) {}
    }
    
    private static void zip(final ZipOutputStream zipOutput, final List<File> sourceFiles, final BufferedOutputStream bufferedOutput) {
        try {
            if (sourceFiles.size() > 0) {
                for (final File sourceFile : sourceFiles) {
                    if (sourceFile.isDirectory()) {
                        final File[] childFiles = sourceFile.listFiles();
                        final List<File> files = new ArrayList<File>();
                        for (final File childFile : childFiles) {
                            files.add(childFile);
                        }
                        zip(zipOutput, files, bufferedOutput);
                    }
                    else {
                        zipOutput.putNextEntry(new ZipEntry(sourceFile.getName()));
                        final FileInputStream FileInput = new FileInputStream(sourceFile);
                        final BufferedInputStream bufferedInput = new BufferedInputStream(FileInput);
                        int b;
                        while ((b = bufferedInput.read()) != -1) {
                            bufferedOutput.write(b);
                        }
                        bufferedInput.close();
                        FileInput.close();
                        bufferedOutput.flush();
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static boolean doc2XmlFile(final Document document, final String filename) {
        boolean flag = true;
        try {
            final TransformerFactory tFactory = TransformerFactory.newInstance();
            final Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            final DOMSource source = new DOMSource(document);
            final StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        }
        catch (Exception ex) {
            flag = false;
        }
        return flag;
    }
    
    public static long getTodaySequence() {
        try {
            final String date = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
            final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return parse(date, dateFormat).getTime();
        }
        catch (Exception ex) {
            return System.currentTimeMillis();
        }
    }
    
    public static Date parse(final String source, final DateFormat formater) throws Exception {
        return formater.parse(source);
    }
    
    static {
        Common.filePaths = new LinkedList<String>();
    }
}
