package com.bjzcyl.service.thumb;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ThumbService {
    private Logger log = Logger.getLogger(ThumbService.class);

    /**
     * Async Create Thumb
     * @param imgPath
     * @param size
     */
    @Async
    public void asyncThumb(String imgPath, int size) {
         String s=thumbImg(imgPath, size, 0);
        log.debug(String.format("图片压缩完成:%s",s));
    }

    /**
     * Sync Create Thumb
     * @param imgPath
     * @param size
     * @return
     */
    public String thumb(String imgPath, int size) {
        return thumbImg(imgPath, size, 0);
    }

    /**
     * Get thumb path from Origin Image
     * @param imgPath
     * @return
     */

    public static String thumbPath(String imgPath) {
        if (StringUtils.isEmpty(imgPath)) {
            return imgPath;
        }
        Path path=Paths.get(imgPath);
        Path thumbPath = Paths.get(path.getParent().toString(), Paths.get("thumb", path.getFileName().toString()).toString());
        return thumbPath.toString();
    }

    public static String getLocalPath(String thumbPath) {
        return thumbPath.replace("thumb", "");
    }

    private String thumbImg(String imgPath, int size,int count) {
        log.info(count);
        if (count > 2) {
            return imgPath;
        }

        Path path = Paths.get(imgPath);
        log.info(imgPath);
        File out = null;
        long currentSize = 0;
        if (!imgPath.contains("thumb")) {
            out = getThumbPath(imgPath).toFile();
        } else {
            out = path.toFile();
        }
        currentSize = path.toFile().length();
        if (currentSize > size || count == 0) {
            try {
                log.info(currentSize);
                Thumbnails.of(path.toFile()).scale(0.7).outputQuality(0.7).toFile(out);
                return thumbImg(out.toPath().toAbsolutePath().toString(), size, ++count);
            } catch (IOException e) {
                throw new RuntimeException("压缩图片失败",e);
            }
        } else {
            return imgPath;
        }
    }


    private Path getThumbPath(String imgPath) {
        Path path=Paths.get(imgPath);
        String root = path.getRoot().toString();
        String[]names=new String[path.getNameCount()-1];
        for(int i=0;i<path.getNameCount()-1;i++) {
            names[i] = path.getName(i).toString();
        }
        Path p=Paths.get(Paths.get(root,names).toString(),Paths.get("thumb",path.getFileName().toString()).toString());

        File pf=p.getParent().toFile();
        if (!pf.exists()) {
            pf.mkdirs();
        }
        return p;
    }


    public static void main(String[] args) {
//        new ThumbService().thumb("E:\\1.jpg",1024*1024);
    String s=new ThumbService().thumbImg("C:\\Users\\admin\\Desktop\\1.jpg",1024*500,0);
        System.out.println(s);
//        System.out.println(new ThumbService().getThumbPath("C:\\Users\\admin\\Desktop\\72471470806815567.jpg"));
    }
}
