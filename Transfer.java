import java.rmi.*;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

interface Transfer extends Remote{

	int initiate() throws RemoteException;
	ArrayList<ImageView> getStatics() throws RemoteException;
	ArrayList<ImageView> getMovables() throws RemoteException;
}