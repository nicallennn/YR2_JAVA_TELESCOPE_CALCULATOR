package tellscopeV4;

public class TeleScopeReflect extends TeleScopeRefract {
	
	TeleScopeReflect(double focalRatio, double lensDiameter, double eyePieceFocalLength)
	{
		super(eyePieceFocalLength, eyePieceFocalLength, eyePieceFocalLength);
		
	}
	
	
	
	//calcDistToSecond method
	public double calcDistToSecond()
	{
		//calculate outer diameter of tube
		outerDiameter = lensDiameter + 1;
		
		//calculate distance to second equation
		distToSecond = (lensDiameter * focalRatio) - ((outerDiameter / 2) + RIH + 1);
		
		//return the distance to second
		return distToSecond;
	}
	
	
	//calcSecondarySizeMinor method
	public double calcSecondarySizeMinor()
	{
		//calculate the secondary size major axis equation
		secondarySizeMinor = ((outerDiameter / 2) + (RIH + RACKTRAVEL)) / focalRatio;
		
		//return the secondary size major axis
		return secondarySizeMinor;
	}
	
	//calcSecondarySizeMajor method
	public double calcSecondarySizeMajor()
	{
		//calculate secondary size minor equation
		secondarySizeMajor = secondarySizeMinor * SECONDEQCONSTANT;
			
		//return the secondary size minor axis
		return secondarySizeMajor;
	}
	
	

}
