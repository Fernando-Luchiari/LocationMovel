import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;


public class EnviaCoordenadas extends Thread{
	private double latitude,longitude;
	private String url= "http://localhost:8080/sgft/ServletMovel";
	private String login= null;
	private String senha= null;
	private String operacao = null;
	
	public EnviaCoordenadas(double latitude,double longitude,String operacao,String login, String senha) {
		super();
		this.latitude=latitude;
		this.longitude=longitude;
		this.operacao=operacao;
		this.login = login;
		this.senha=senha;
	}

	
	public void run() {
		try 
	      {
			HttpConnection con=null;
			String url2=null;	        	
			url2= this.url+"?operacao="+ operacao+"&id="+login+"&senha="
        	+senha+"&latitude="+latitude+"&longitude="+longitude;			
	      con = (HttpConnection) Connector.open(url2);
	      int rc = con.getResponseCode();
	      con.close();
	       System.out.println(rc);
	      System.out.println(url2);
	      }catch (Exception ex){
	    	  ex.printStackTrace();
	      }
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getOperacao() {
		return operacao;
	}


	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
}
