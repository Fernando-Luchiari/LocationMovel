import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class Welcome extends Form  {

	private Command acessar =  new Command("Logout", Command.OK, 1);
	private StringItem si,si2;
	public Welcome(String title) {
		super(title);		
		si= new StringItem("Seja Bem Vindo!",null);
		si2= new StringItem(null,"você está on line");
		addCommand(acessar);
		append(si);
		append(si2);
	}
	public Command getAcessar() {
		return acessar;
	}
	public void setAcessar(Command acessar) {
		this.acessar = acessar;
	}

}
