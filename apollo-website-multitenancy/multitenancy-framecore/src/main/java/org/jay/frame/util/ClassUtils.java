package org.jay.frame.util;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtils {
//	
//	public static List<Class> getAllClassByInterface(Class c){
//		List<Class> returnClassList = new ArrayList<Class>();
//		
//		if(c.isInterface()){
//			String packageName = c.getPackage().getName();
//			try{
//				List<Class> allClass = getClasses(packageName);
//				for(int i=0; i<allClass.size(); i++){
//					if(c.isAssignableFrom(allClass.get(i))){
//						if(!c.equals(allClass.get(i))){
//							returnClassList.add(allClass.get(i));
//						}
//					}
//				}
//			}catch(ClassNotFoundException e){
//				e.printStackTrace();
//			}catch(IOException e){
//				e.printStackTrace();
//			}
//		}
//		return returnClassList;
//	}
	
	
	
	
	
	public static List<String> getClasses(String packageName) throws ClassNotFoundException,IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace(".", "/");
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while(resources.hasMoreElements()){
			URL resource = resources.nextElement();
			String pathUrl = resource.getFile();
			pathUrl = pathUrl.replace("%20", " ");
			dirs.add(new File(pathUrl));
		}
		ArrayList<String> classes = new ArrayList<String>();
		for(File directory :dirs){
			classes.addAll(findClasses(directory,packageName));
		}
		return classes;
	}
	
	private static List<String> findClasses(File directory,String packageName) throws ClassNotFoundException{
		
		List<String> classes = new ArrayList<String>();
		if(!directory.exists()){
			return classes;
		}
		
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,packageName+"."+file.getName()));
			}else if(file.getName().endsWith(".class")){
				classes.add(packageName+"."+file.getName().substring(0,file.getName().length() -6));
			}
		}
		return classes;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		 List<String> arr = getClasses("com.jay.sys.model");
		 System.out.println(arr.size());
		 for (int i = 0; i < arr.size(); i++) {
			 String c = arr.get(i);
			 System.out.println(c);
		 }
	}
}