import java.util.Comparator;

public class EuclideanComparator implements Comparator<MnistObject> {

	//Used to compare two objects. 
	//Returns 0 if objects are equal. 
	//Returns positive value is o1 is bigger than o2.
	
	@Override
	public int compare(MnistObject o1, MnistObject o2) {
		// TODO Auto-generated method stub
		return (int) (o2.getEuclideanDistance() - o1.getEuclideanDistance());
	}

}
