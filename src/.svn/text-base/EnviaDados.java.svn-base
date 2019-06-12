import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;


public class EnviaDados extends Thread{
	
	private String url= "http://localhost:8080/sgft/ServletMovel";
	private String login= null;
	private String senha= null;
	private String operacao = null;
	private String status = null;
	private RecuperaCoordenadas mid;
	
	
	public EnviaDados(String login, String senha,String operacao) {
		super();
		this.login = login;
		this.senha=senha;
		this.operacao=operacao;
		
	}
	public EnviaDados(String login, String senha,String operacao,RecuperaCoordenadas mid) {
		super();
		this.login = login;
		this.senha=senha;
		this.operacao=operacao;
		this.mid=mid;
	}
	public void run() {
		try 
	      {
			HttpConnection con=null;
			String url2=null;	        	
	        url2= this.url+"?operacao="+operacao+"&id="+login+"&senha="+senha;			
	        con = (HttpConnection) Connector.open(url2);
	    	int rc = con.getResponseCode();
	    	setStatus("Status " + rc );
		    con.close();
		    System.out.println(rc);	      
	        System.out.println(url2);
	      }catch (Exception ex){
	    	  ex.printStackTrace();
	    	  setStatus("Status " + 20 );
	      }
	}
	
	public void disparar() throws IOException{
			
   	 }	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public void  setStatus(String status) {
		this.status = status;
		mid.setStatus(status);
	}
	public String getStatus() {
		return status;
	}
}
