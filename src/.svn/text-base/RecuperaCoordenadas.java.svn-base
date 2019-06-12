import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class RecuperaCoordenadas extends MIDlet implements CommandListener  {
	  private Display display;
	  public Form form;
	  public TextField login,senha;
	  private String id,pass=null;
	  private Command acessar;
	  private Welcome welcome= new Welcome("SGCT - Mobile");  
	  private String status=null;
	  private boolean flag=false;
	  private boolean logout=false;
	  Provider provider = null;
	  
	public RecuperaCoordenadas() {
		    display = Display.getDisplay(this);    
		    form = new Form("SGCT - acesso");		    
		    acessar= new Command("OK", Command.OK, 1);
		    login = new TextField("ID:","",10,TextField.ANY);
			senha = new TextField("Senha:","",10,TextField.PASSWORD);			
		    form.append(login);
		    form.append(senha);
		    form.addCommand(acessar);		   
		    form.setCommandListener(this);    
	}
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		if (!logout){
			final EnviaDados envialogout = new EnviaDados(id,pass,"logoutMotorista");
	    	envialogout.start();
	    	logout=false;
	    	
	    }
		if((provider!=null) && provider.isAlive()){
    		provider.setStatus(false);
    	}
	}
	protected void pauseApp() {
	}
	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(form);
	}
	public Display getDisplay() {
		return display;
	}
	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
	    if (label.equals("OK")){
	    	 if (validarCampos()){
	    		id=login.getString();
	    		pass=senha.getString();
	    		Confirmacao();
	    	 }
	    }
				
	}	
	public boolean validarCampos(){
		if (login.getString().equals("")){
			Alert alert = new Alert("Alerta","Campo de login vazio !", null, AlertType.INFO);
			display.setCurrent(alert);
			return false;
		}else if(senha.getString().equals("")){
			Alert alert = new Alert("Alerta","Campo de senha vazio!", null, AlertType.INFO);
			display.setCurrent(alert);
			return false;
		}
		final EnviaDados enviadados= new EnviaDados(login.getString(),senha.getString(),"AcessoMotorista",this);		 
		enviadados.start();
		if(status == null){
    		Confirmacao();
		}
		return true;
		
	}
	public void Confirmacao(){	
		 Alert alerta = new Alert("Alerta","Deseja Prosseguir?", null, AlertType.CONFIRMATION);
		 alerta.setTimeout(Alert.FOREVER);
		 alerta.addCommand(new Command("Sim", Command.OK, 0));
		 alerta.addCommand(new Command("Não", Command.CANCEL, 0));
		 alerta.setCommandListener(new CommandListener() {     
		        public void commandAction(Command command, Displayable telaAlert) {     
		            switch (command.getCommandType()) {     
		                case Command.OK:
		                	 validarStatus();
		                	 break;						                         
		                case Command.CANCEL:     
		                    display.setCurrent(form); //Mostra a tela anterior     
		                    break;     
		            }     
		        }
		    });
		 display.setCurrent(alerta);  			 
}

	public boolean validarStatus(){		
		 if (getStatus().equals("Status 200")){
			 flag=true;
			 AcessoGPS();
			 return true;
		 }else if (getStatus().equals("Status 20")){
			 flag=false;
	         Alert alert = new Alert("Erro","O Servidor está fora do ar!", null, AlertType.ERROR);
	         display.setCurrent(alert,form);
	         status=null;
			 return false;
		 }else{
			 flag=false;
	         Alert alert = new Alert("Alerta","Login ou senha inválidos!", null, AlertType.INFO);
	         display.setCurrent(alert,form);
	         status=null;
			 return false;
		 }				
	}
	
	public void AcessoGPS(){
		if (flag){
			 senha.setString("");
			 login.setString("");
			 display.setCurrent(welcome);
			 CommandListener commandListener = new CommandListener() {
		        public void commandAction(Command c, Displayable d) {
		        	if (c == welcome.getAcessar()){
						Alert alerta = new Alert("Alerta","Deseja sair do Sistema?", null, AlertType.CONFIRMATION);
						alerta.addCommand(new Command("Sim", Command.OK, 0));     
					    alerta.addCommand(new Command("Não", Command.CANCEL, 1));     
					    alerta.setTimeout(Alert.FOREVER);     
					    //Define as ações dos ccomandos SIM e NAO     
					    alerta.setCommandListener(new CommandListener() {     
					        public void commandAction(Command command, Displayable telaAlert) {     
					            switch (command.getCommandType()) {     
					                case Command.OK:  //efetua o logout do motorista caso ele selecione sim
					                	final EnviaLogout envialogout = new EnviaLogout(id,pass,"logoutMotorista");
					                	envialogout.start();
					                	logout=true;
					                	if(provider.isAlive()){
					                		provider.setStatus(false);
					                	}
					                	setStatus(null);
					                	display.setCurrent(form);
					                	break;						                         
					                case Command.CANCEL:     
					                    display.setCurrent(welcome); //Mostra a tela anterior     
					                    break;     
					            }     
					        }
					    });
						display.setCurrent(alerta);	    	
			    	 }
		        }
		    };
		     welcome.setCommandListener(commandListener);
		     display.setCurrent(welcome);
		     setStatus(null);
		     logout=false;
		     provider=new Provider();
		     provider.setLogin(id);
			 provider.setSenha(pass);
			 provider.start();
			 
		 }
	}    	 

	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}