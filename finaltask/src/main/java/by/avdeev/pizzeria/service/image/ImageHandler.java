package by.avdeev.pizzeria.service.image;

import javax.servlet.http.Part;

/**
 * Handler class for working with images.
 */
public interface ImageHandler {
    /**
     * Receives image name.
     *
     * @param part Input from user.
     * @return Filename.
     */
    String receiveImageName(final Part part);

    /**
     * Uploads image into the target.
     *
     * @param part     Input from user.
     * @param pathname Path to the target.
     * @param fileName Name of image file.
     * @return True if it was uploaded else false.
     */
    boolean upload(final Part part,
                   final String pathname,
                   final String fileName);

    /**
     * Copies of existing image.
     *
     * @param srcPath  Pathname from it copies.
     * @param destPath Pathname to it copies.
     * @param fileName Name of image.
     * @return True if it was copied.
     */
    boolean copy(final String srcPath,
                 final String destPath,
                 final String fileName);

    /**
     * Deletes image.
     *
     * @param imageName Name of dest image.
     * @param pathname  Pathname to image.
     * @return True if it was deleted.
     */
    boolean delete(String imageName, String pathname);

    /**
     * Validates image on correct format.
     *
     * @param filename Name of image.
     * @return True if it is correct else false.
     */
    boolean validate(String filename);
}
