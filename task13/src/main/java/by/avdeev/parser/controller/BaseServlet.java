package by.avdeev.parser.controller;

import by.avdeev.parser.entity.Order;
import by.avdeev.parser.service.ParserService;
import by.avdeev.parser.service.ServiceException;
import by.avdeev.parser.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@MultipartConfig
public class BaseServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter are {}, {}";
    private final static String RESULT = "return value is {}";

    private static final String IMAGE_PARAMETER_NAME = "data";

    private String getFileName(final Part part) {
        logger.debug(START);
        logger.debug("parameter is {}", part);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String res = content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
                logger.debug(RESULT, res);
                return res;
            }
        }
        return null;
    }

    private void pipe(ReadableByteChannel in, WritableByteChannel out) throws IOException {
        logger.debug(START);
        logger.debug(PARAM, in, out);
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) >= 0 || buffer.position() > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.compact();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug(START);
        logger.debug(PARAM, request, response);
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + "resources\\orders";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        final Part image = request.getPart(IMAGE_PARAMETER_NAME);
        final String name = this.getFileName(image);
        if (image.getSize() > 0 && image.getInputStream() != null && name != null) {
            final Path outputFile = Paths.get(fileSaveDir.getAbsolutePath(), name);
            try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
                 final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()))) {
                pipe(input, output);
            }
        }
        ServiceFactory factory = ServiceFactory.getFactory();
        ParserService parserService = factory.getParserService();
        String typeParser = request.getParameter("typeParser");
        Set<Order> orders = null;
        String pathname = fileSaveDir.getAbsolutePath() + File.separator + name;
        try {
            orders = parserService.parse(pathname, typeParser);
        } catch (ServiceException e) {
            logger.error(e);
        }
        request.setAttribute("type", typeParser);
        request.setAttribute("orders", orders);
        try {
            request.getRequestDispatcher("output.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
