import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;


public class Provider extends Thread implements LocationListener {
	private String login= null;
	private String senha= null;	
	private boolean status=true;
	
	public void run() {
		while (status) { 
			try 
		      {
		         PegaCoordenadas();
		         Thread.sleep(300000);
		      } 
		      catch (Exception ex)
		      {
		         ex.printStackTrace();   
		      }
		}
   }
	public void PegaCoordenadas()throws Exception{
		LocationProvider provider ;
		Location location;
		Criteria cr = new Criteria();
		Coordinates c = null;
		cr.setHorizontalAccuracy(100);
		cr.setVerticalAccuracy(100);
		cr.setPreferredPowerConsumption(Criteria.POWER_USAGE_HIGH);
		cr.setPreferredResponseTime(Criteria.NO_REQUIREMENT);	   
		provider = LocationProvider.getInstance(cr);		
		location = provider.getLocation(1200);	//20 minutos 600=10 minutos
		if (location != null){
			c = location.getQualifiedCoordinates();
		}		
		if(c != null && status == true) {
			//formatando as coordenadas de latitude e longitude para 6 casas apos a virgula			
			double latitude = (double)(int)((c.getLatitude()+0.0000005)*1000000.0)/1000000.0;	
			double longitude = (double)(int)((c.getLongitude()+0.0000005)*1000000.0)/1000000.0;
			System.out.println(latitude);
			System.out.println(longitude);
			EnviaCoordenadas ed = new EnviaCoordenadas(latitude,longitude,"GravarCoordenadas",login,senha);
			ed.start();	       
		} 
		
	}
	
	public void locationUpdated(LocationProvider arg0, Location arg1) {
		// TODO Auto-generated method stub
		
	}
	public void providerStateChanged(LocationProvider arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return login;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

	

}
