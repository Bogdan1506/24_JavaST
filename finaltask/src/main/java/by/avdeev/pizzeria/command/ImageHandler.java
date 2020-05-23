package by.avdeev.pizzeria.command;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import static by.avdeev.pizzeria.command.ConstantRepository.CONTENT_DISPOSITION;
import static by.avdeev.pizzeria.command.ConstantRepository.FILENAME;

public class ImageHandler {
    public String receiveImageName(final Part part) {
        String fileName = null;
        if (part.getSize() > 0) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
            String[] tokens = contentDisposition.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith(FILENAME)) {
                    fileName = token.substring(token.indexOf('=') + 2, token.length() - 1);
                }
            }
        }
        return fileName;
    }

    public boolean upload(final Part part,
                          final String pathname,
                          final String fileName) {
        try {
            File fileSaveDir = new File(pathname);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            part.write(pathname + File.separator + fileName);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean copy(final String srcPath,
                        final String destPath,
                        final String fileName) {
        File fileSaveDir = new File(destPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        try {
            File file = new File(srcPath + File.separator + fileName);
            OutputStream os = new FileOutputStream(new File(destPath + File.separator + fileName));
            Files.copy(file.toPath(), os);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean delete(String imageName, String pathname) {
        boolean isDeleted = false;
        if (!imageName.equals("pizza_not_found.jpg")) {
            try {
                File firstFile = new File(pathname + File.separator + imageName);
                isDeleted = Files.deleteIfExists(firstFile.toPath());
            } catch (IOException e) {
                return false;
            }
        }
        return isDeleted;
    }
}
