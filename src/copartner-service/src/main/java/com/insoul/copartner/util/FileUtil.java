package com.insoul.copartner.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;

public final class FileUtil {

    private FileUtil() {
    }

    public static String getFileType(final String fileName) {
        String fileType = "";
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            fileType = fileName.substring(index + 1);
        }
        return fileType;
    }

    public static boolean validateFileType(final String fileName, final String[] types) {
        String fileType = FileUtil.getFileType(fileName);
        if (types.length > 0) {
            if (StringUtils.isNotBlank(fileType)) {
                for (String type : types) {
                    if (fileType.equalsIgnoreCase(type)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static void validateFile(final MultipartFile file, final long maxSize, final long minSize,
            final String[] types) throws CException {
        if ((null != file) && !file.isEmpty()) {
            if ((maxSize > 0) && (file.getSize() > maxSize)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_BEYOND_MAXSIZE);
            }
            if ((minSize > 0) && (file.getSize() < minSize)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_ABSENT_MINSIZE);
            }
            if (!FileUtil.validateFileType(file.getOriginalFilename(), types)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_TYPE_INCORRECT);
            }
        } else {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_IS_EMPTY);
        }
    }

    public static void validateImage(final MultipartFile file, final long maxSize, final long minSize,
            final String[] types, final int maxWidth, final int minWidth, final int maxHeight, final int minHeight)
            throws CException {
        validateFile(file, maxSize, minSize, types);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.IMAGE_VALIDATE_ERROR);
        }
        int imageWidth = image.getWidth(), imageHeight = image.getHeight();
        if (imageWidth > maxWidth) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.IMAGE_BEYOND_MAXWIDTH);
        } else if (imageWidth < minWidth) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.IMAGE_ABSENT_MINWIDTH);
        }
        if (imageHeight > maxHeight) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.IMAGE_BEYOND_MAXHEIGHT);
        } else if (imageHeight < minHeight) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.IMAGE_ABSENT_MINHEIGHT);
        }
    }

    public static void saveFile(final InputStream in, final String path, final String fileName) throws IOException {
        OutputStream os = null;
        try {
            File distDir = new File(path);
            if (!distDir.exists()) {
                distDir.mkdirs();
            }
            os = new FileOutputStream(new File(distDir, fileName));
            byte[] buffer = new byte[256];
            int len = in.read(buffer);
            while (len != -1) {
                os.write(buffer, 0, len);
                len = in.read(buffer);
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            if (null != os)
                os.close();
            if (null != in)
                in.close();
        }
    }

    public static BufferedImage scaleAndCutImage(final BufferedImage bufferedImage, final int targetW, final int targetH) {
        return cutImage(scaleImage(bufferedImage, targetW, targetH), targetW, targetH);
    }

    public static BufferedImage scaleImage(final BufferedImage source, final int targetW, final int targetH) {
        return resizeImage(source, targetW, targetH, true);
    }

    public static BufferedImage resizeImage(final BufferedImage source, final int targetW, final int targetH,
            final boolean isProportion) {
        if ((targetW <= 0) || (targetW <= 0)) {
            return source;
        }

        int width = targetW;
        int height = targetH;
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) width / source.getWidth();
        double sy = (double) height / source.getHeight();
        if (isProportion) {
            if (sx < sy) {
                sx = sy;
                width = (int) (sx * source.getWidth());
            } else {
                sy = sx;
                height = (int) (sy * source.getHeight());
            }
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(width, height, type);
        }
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static BufferedImage cutImage(final BufferedImage bufferedImage, final int targetW, final int targetH) {
        int sourceWidth = bufferedImage.getWidth();
        int sourceHeight = bufferedImage.getHeight();

        int dw = targetW, dh = targetH;
        if (targetW > sourceWidth) {
            dw = sourceWidth;
        }
        if (targetH > sourceHeight) {
            dh = sourceHeight;
        }
        int x = 0, y = 0, sw = 0, sh = 0;
        x = (sourceWidth / 2) - (dw / 2);
        y = (sourceHeight / 2) - (dh / 2);
        sw = (sourceWidth / 2) + (dw / 2);
        sh = (sourceHeight / 2) + (dh / 2);

        BufferedImage destination = null;
        destination = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = destination.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, dw, dh, x, y, sw, sh, null);
        return destination;
    }

    public static long string2bytes(String size) {
        BigDecimal bytes = null;
        if (size.contains("MB")) {
            bytes = new BigDecimal(1024 * 1024).multiply(new BigDecimal(size.replace("MB", "")));
        } else if (size.contains("KB")) {
            bytes = new BigDecimal(1024).multiply(new BigDecimal(size.replace("KB", "")));
        } else if (size.contains("B")) {
            bytes = new BigDecimal(size.replace("B", ""));
        } else {
            bytes = new BigDecimal(0);
        }

        return bytes.longValue();
    }

}
