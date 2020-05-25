package by.avdeev.pizzeria.service.image;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.CONTENT_DISPOSITION;
import static by.avdeev.pizzeria.command.ConstantRepository.FILENAME;

public class ImageHandlerImpl implements ImageHandler {

    /**
     * Receives image name.
     *
     * @param part Input from user.
     * @return Filename.
     */
    @Override
    public String receiveImageName(final Part part) {
        String fileName = null;
        String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith(FILENAME)) {
                fileName = token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return fileName;
    }

    /**
     * Uploads image into the target.
     *
     * @param part     Input from user.
     * @param pathname Path to the target.
     * @param fileName Name of image file.
     * @return True if it was uploaded else false.
     */
    @Override
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

    /**
     * Copies of existing image.
     *
     * @param srcPath  Pathname from it copies.
     * @param destPath Pathname to it copies.
     * @param fileName Name of image.
     * @return True if it was copied.
     */
    @Override
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

    /**
     * Deletes image.
     *
     * @param imageName Name of dest image.
     * @param pathname  Pathname to image.
     * @return True if it was deleted.
     */
    @Override
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

    /**
     * Validates image on correct format.
     *
     * @param filename Name of image.
     * @return True if it is correct else false.
     */
    @Override
    public boolean validate(String filename) {
        if (filename != null) {
            Set<String> allowedFormats = new HashSet<>(Arrays.asList("png", "jpg"));
            for (String format : allowedFormats) {
                if (filename.endsWith(format)) {
                    return true;
                }
            }
        }
        return false;
    }
}
