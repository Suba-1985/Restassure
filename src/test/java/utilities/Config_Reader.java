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
			FileInputStream ip=new FileInputStream("src\\test\\resources\\configFile\\config.properties");
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
	
	
	
	public String baseUrl()
	{
		String url = prop.getProperty("BaseUrl");
		System.out.println(url);
		if(url!=null)
		{
			return url;
		}else
		{
			System.out.println("uri is not mentioned in config properties");
		}	
		return url;	
	}
	
	public static String postProgramEndpoint()
	{
		String postEndPoint = prop.getProperty("PostProgramEndpoint");
		
		if(postEndPoint!=null)
		{
			return postEndPoint;
		}else
		{
			System.out.println("PostProgramEndpoint is not mentioned in config properties");
		}	return postEndPoint;	
	}
	
	public static String getAllEndpoint()
	{   
		
		String getAllPoint=prop.getProperty("GetAllEndpoint");
		if(getAllPoint!=null)
		{
			return getAllPoint;
		}else
		{
			System.out.println("GetAllEndpoint is not mentioned in config properties");
		}	return getAllPoint;	
	}
	
	public static String getOneProgramIdEndpoint() {
		String getIdEndPoint = prop.getProperty("GetOneProgramIdEndpoint");
		if (getIdEndPoint != null)
			return getIdEndPoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	
	public static String putProgramByProgramNameEndpoint() {
		String putProgramNamePoint = prop.getProperty("PutProgramByProgramNameEndpoint");
		if (putProgramNamePoint != null)
			return putProgramNamePoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	public static String putProgramByProgramIdEndpoint() {
		String putbyprogramIdEndPoint = prop.getProperty("PutProgramByProgramIdEndpoint");
		if ( putbyprogramIdEndPoint != null)
			return  putbyprogramIdEndPoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	public static String deleteprogramByidEndpoint() {
		String deletebyidEndPoint = prop.getProperty("DeleteProgramByProgramIdEndpoint");
		if (deletebyidEndPoint != null)
			return deletebyidEndPoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	public static String deleteprogramBynameEndpoint() {
		String deletebynameEndPoint = prop.getProperty("DeleteProgramByProgramNameEndpoint");
		if (deletebynameEndPoint != null)
			return deletebynameEndPoint;
		else
			throw new RuntimeException("deletebynameEndPoint not specified in the Config.properties file");
	}
//	public static String getOneProgramIdEndpoint() {
//		String getIdEndPoint = prop.getProperty("GetOneProgramIdEndpoint");
//		if (getIdEndPoint != null)
//			return getIdEndPoint;
//		else
//			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
//	}
	
	
	public static String excelpath()
	{
		String excelpath=prop.getProperty("excelpath");
		if(excelpath != null)
		return excelpath;
		else throw new RuntimeException("excelpath not specified in the Config.properties file");			
	}
}