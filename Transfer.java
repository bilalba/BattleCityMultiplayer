import java.rmi.*;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

interface Transfer extends Remote{

	int initiate() throws RemoteException;
	public ArrayList<ArrayList<Info>> getit() throws RemoteException;
}
