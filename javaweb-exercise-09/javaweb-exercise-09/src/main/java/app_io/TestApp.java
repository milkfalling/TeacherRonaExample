package app_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestApp {

	public static void main(String[] args) {
		
//		User user = new User();
//		user.setId(1);
//		user.setUsername("Rona1102");
//		user.setPassword("12345678");
//		user.setNickname("rona");
		
		try(
//			FileOutputStream fos = new FileOutputStream("C:/Users/Tibame_T14/Desktop/ronatest.data"); //拿來輸出東西
//			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			
			FileInputStream fis = new FileInputStream("C:/Users/Tibame_T14/Desktop/ronatest.data");
			ObjectInputStream ois = new ObjectInputStream(fis)
		) {
//			oos.writeObject(user);
			User user = (User) ois.readObject();
			System.out.println(user.getId());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getNickname());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
//		String scr = "/C:/Users/Tibame_T14/Desktop/parrot.jpg";
//		String dest = "/C:/Users/Tibame_T14/Desktop/xxx.jpg";
//		try (
//			 FileInputStream fis = new FileInputStream(scr);
//			 BufferedInputStream bis = new BufferedInputStream(fis); //空間大小
//				
//			 FileOutputStream fos = new FileOutputStream(dest);
//			 BufferedOutputStream bos = new BufferedOutputStream(fos);
//			) {
//			
//			byte[] data = new byte[bis.available()];
//			bis.read(data);
//			bos.write(data);
//			bos.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
