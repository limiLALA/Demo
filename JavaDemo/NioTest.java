import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NioTest {
 
	public static void main(String[] args) throws Exception{
		//µÃµ½ÎÄ¼þÊäÈëÁ÷
		FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\23637\\Pictures\\Menhera-chan\\4.jpg"));
		//µÃµ½ÊäÈëChannelÍ¨µÀ
		FileChannel inChannel = fileInputStream.getChannel();
		//µÃµ½bytebuffer»º³åÇø
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		//µÃµ½Êä³öÁ÷
		FileOutputStream fileOutputStream = new FileOutputStream("4.jpg");
		//µÃµ½Êä³öÍ¨µÀ
		FileChannel outChanner = fileOutputStream.getChannel();
		int eof = -1;
		//Èç¹û¶ÁÈ¡µ½µÄ³¤¶È²»Îª-1Ôò»º³åÇøÓÐÊý¾Ý
		while((eof=inChannel.read(byteBuffer))!=-1){
			//´Ó¶ÁÄ£Ê½ÇÐ»»µ½Ð´Ä£Ê½
			byteBuffer.flip();
			//Ð´Èëµ½Ö¸¶¨ÎÄ¼þ
			outChanner.write(byteBuffer);
			//Çå¿Õ»º³åÇø
			byteBuffer.clear();
		}
		//¹Ø±ÕÍ¨µÀºÍÊäÈëÊä³öÁ÷
		outChanner.close();
		fileOutputStream.close();
		inChannel.close();
		fileInputStream.close();
	}
}