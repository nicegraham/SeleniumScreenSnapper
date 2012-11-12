package org.seleniumscreensnapper;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.imgscalr.Scalr.crop;
import static org.imgscalr.Scalr.resize;

public class Thumbnailer {
    private final int thumbnailWidth = 500;
    private final int thumbnailHeight = 500;
    private String thumbnailFolder;

    public Thumbnailer(String thumbnailFolder) {
        this.thumbnailFolder = thumbnailFolder;
    }

    public void createAndSaveThumbnail(File file, WebElement element) {
        createAndSaveThumbnail(file, 0, element);
    }

    public void createAndSaveThumbnail(File file, int index, WebElement element) {
        Point centerPoint = getCenter(element);
        Dimension elementDimension = element.getSize();
        BufferedImage image = loadImage(file);
        if (centerPoint != null) {
            Point cropPoint = findImageCropPoint(centerPoint, image);
            int cropWidth = thumbnailWidth;
            int cropHeight = thumbnailHeight;

            if (elementDimension.getWidth() > thumbnailWidth) {
                int newXPoint = cropPoint.getX() - (elementDimension.getWidth() / 2);
                if (newXPoint < 0) {
                    newXPoint = 0;
                }
                cropPoint = new Point(newXPoint, cropPoint.getY());
            }

            if (image.getHeight() < thumbnailHeight) {
                cropHeight = image.getHeight();
            }

            if (image.getWidth() < thumbnailWidth) {
                cropWidth = image.getWidth();
            }

            image = crop(image, cropPoint.getX(), cropPoint.getY(), cropWidth, cropHeight);
        } else {

            if ((image.getHeight() > thumbnailHeight) || (image.getWidth() > thumbnailWidth)) {
                image = resize(image, thumbnailWidth);
            }
        }
        writeThumbnailFileToDisk(file, index, image);
    }

    private Point getCenter(WebElement element) {
        Point topLeft = LocationFinder.getLocation(element);
        Dimension dimension = element.getSize();
        return new Point((topLeft.getX() + (dimension.width / 2)), (topLeft.getY() + (dimension.height / 2)));
    }

    private Point findImageCropPoint(Point centerPoint, BufferedImage image) {
        return new Point(transposeXCoordinate(centerPoint.getX(), image), transposeYCoordinate(centerPoint.getY(), image));
    }

    private int transposeXCoordinate(int coordinate, BufferedImage image) {
        return transposeCoordinate(coordinate, thumbnailWidth, image.getWidth());
    }

    private int transposeYCoordinate(int coordinate, BufferedImage image) {
        return transposeCoordinate(coordinate, thumbnailHeight, image.getHeight());
    }

    private int transposeCoordinate(int coordinate, int thumbnailSize, int imageSize) {
        int transposedCoordinate;
        if (coordinate + (thumbnailSize / 2) > imageSize) {
            transposedCoordinate = (imageSize - thumbnailSize) > 0 ? (imageSize - thumbnailSize) : 0;
        } else if (coordinate > (thumbnailSize / 2)) {
            transposedCoordinate = coordinate - (thumbnailSize / 2);
        } else {
            transposedCoordinate = 0;
        }
        return transposedCoordinate;
    }

    private void writeThumbnailFileToDisk(File file, int index, BufferedImage image) {
        String filename = thumbnailFolder + System.getProperty("file.separator") + file.getName();
        if (index > 0) {
            filename += "_" + index;
        }
        System.out.println(filename);
        File thumbnail = new File(filename);
        try {
            ImageIO.write(image, "png", thumbnail);
        } catch (IOException e) {
            System.out.println("Problem writing thumbnail '" + filename + "'" + e);
        }
    }

    private BufferedImage loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Problem reading thumbnail" + e);
        }
        return image;
    }
}