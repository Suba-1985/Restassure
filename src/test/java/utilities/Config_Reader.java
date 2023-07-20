package utilities;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config_Reader {
	private static Properties prop;
	
	public Properties init_prop() throws IOException
	{
		prop=new Properties();
		try
		{
			FileInputStream ip=new FileInputStream("src\\test\\resources\\Config_property\\config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
		e.printStackTrace();	
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
	public String baseUri()
	{
		String uri = prop.getProperty("BaseUri");
		System.out.println(uri);
		if(uri!=null)
		{
			return uri;
		}else
		{
			System.out.println("uri is not mentioned in config properties");
		}	
		return uri;	
	}
	
	public static String endpoint()
	{
		String getEndPoint = prop.getProperty("Endpoint");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	
	public static String postReqEndpoint()
	{   
		
		String postEndPoint=prop.getProperty("PostEndpoint");
		if(postEndPoint!=null)
		{
			return postEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return postEndPoint;	
	}
	
	public static String putReqEndpoint() {
		String putEndPoint = prop.getProperty("PutEndpoint");
		if (putEndPoint != null)
			return putEndPoint;
		else
			throw new RuntimeException("putEndPoint not specified in the Config.properties file");
	}
	
	public  String excelpath()
	{
		String excelpath=prop.getProperty("excelpath");
		if(excelpath != null)
		return excelpath;
		else throw new RuntimeException("excelpath not specified in the Config.properties file");			
	}
}