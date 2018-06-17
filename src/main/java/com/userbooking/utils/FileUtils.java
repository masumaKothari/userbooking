package com.userbooking.utils;

import com.google.common.base.Preconditions;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;

public class FileUtils {

    public static final String FILE = "file:///";
    public static final String HTTP = "http";
    public static final String CLASSPATH = "classpath:";


    public static InputStream getInputStream(String filePath) throws IOException {
        InputStream is;
        if (filePath.startsWith(HTTP)) {
            UrlResource resource = new UrlResource(filePath);
            is = resource.getInputStream();
        } else if (filePath.startsWith(FILE)) {
            String fileS = filePath.substring(FILE.length());
            File file = new File(fileS);
            checkArgument(file.exists(), "File [%s] not found on FS", filePath);
            is = new FileInputStream(file);
        } else if (filePath.startsWith(CLASSPATH)) {
            filePath = filePath.replace("classpath:", "/");
            is = FileUtils.class.getResourceAsStream(filePath);
            Preconditions.checkNotNull(is, "Resource [%s] not found on classpath", filePath);
        } else {
            is = new FileInputStream(filePath);
        }
        return is;
    }
}
