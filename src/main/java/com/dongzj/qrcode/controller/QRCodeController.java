package com.dongzj.qrcode.controller;

import com.dongzj.qrcode.util.QRCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 11:57
 */
@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

    @GetMapping(value = "/create", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage generateQRCode(@RequestParam String text) {
        int width = 260;
        int height = 260;
        String format = "png";
        long now = System.currentTimeMillis();
        String filePath = "/Users/dongzj/Workspaces/test/qr-code/src/main/resources/qrcode/" + now + ".png";
        try {
            String fileName = QRCodeUtil.generateQRCode(text, width, height, format, filePath);
            BufferedImage bufferedImage = ImageIO.read(new File(fileName));
            return bufferedImage;
        } catch (Exception e) {
            logger.error("generateQRCode fail, msg: {}", e.getMessage());
        }
        return null;
    }
}
