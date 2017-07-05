package org.eclipse.kapua.app.common.util;
//org.eclipse.kapua.app.api
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class EncodeHelper {
	public static byte[] getQRCode(String content , int _width) throws Exception{
        int width = _width;  
        int height = _width;  
        //二维码的图片格式  
        String format = "png";
        Hashtable hints = new Hashtable();
        //内容所使用编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, width, height, hints);
        //生成二维码 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, out);
        byte[] b = out.toByteArray();
        
        return b;
        
	}
}
