package com.example.warehouse.shared;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BarCodeGeneration {

    public static byte[] barCodeGeneration(String content,int height,int width) throws WriterException , IOException {

        Code128Writer code128Writer = new Code128Writer();
        BitMatrix bitMatrix = code128Writer.encode(content, BarcodeFormat.CODE_128,width,height);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
