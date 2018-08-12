/*
 * Name: TellsCalculations Class
 * Date: 27/2/17
 * Version: v1
 * Description: This class contains all the methods to perform the telescope calculations
 *  
 *  */

package tellscopeV4;



public class TellsCalculations {
	
	//constructor for TellsCalculations object
	TellsCalculations(double focalRatio, double lensDiameter, double eyePieceFocalLength)
	{
		this.focalRatio = focalRatio;
		this.lensDiameter = lensDiameter;
		this.eyePieceFocalLength = eyePieceFocalLength;
			
	}
	
	//define constants for RIH and RACKTRAVEL and SECONDAYEQCONSTANT
	private static final double RIH = 3.5;
	private static final double RACKTRAVEL = 1;
	private static final double SECONDEQCONSTANT = 1.414;
	
	//variables to store focal ratio, main lens, eyepiece focal length
	private double focalRatio;
	private double lensDiameter;
	private double eyePieceFocalLength;
	private double outerDiameter;
	
	//variables to store calculated results
	private double focalLength;
	private double tubeLength;
	private double distToSecond;
	private double secondarySizeMinor;
	private double secondarySizeMajor;
	private double minMagnitude;
	private double minResolution;
	private double maxVisibleMagnification;
	private double minVisibleMagnification;
	private double eyePieceMagnification;
	
	//variables to store lens diameter and eyepiece length using different scales
	private double lensDiameterCm;
	private double lensDiameterMm;
	
	
	

	/* 
	 * methods to calculate:
	 * focal length
	 * tube length
	 * distance to secondary
	 * secondary size
	 * minimum magnitude
	 * minimum resolution
	 * magnification limits - 2 methods (calcMaxVisibleMag, calcMinVisibleMag)
	 * eyepiece magnification
	 * 
	 * */
	
	//calcFocalLength method
	public double calcFocalLength()
	{
		//calculate focal length equation
		focalLength = lensDiameter * focalRatio;
		
		//return the focal length
		return focalLength;
	}
	
	//calcTubeLength method
	public double calcTubeLength()
	{
		//calculate tube length equation
		tubeLength = lensDiameter * focalRatio;
		
		//return the tube length
		return tubeLength;
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
	
	//calcMinMagnitude method
	public double calcMinMagnitude()
	{
		
		//get lens diameter in cm
		lensDiameterCm = lensDiameter * 2.54;
			
		//calculate the minimum magnitude equation
		//minMagnitude = 7.5 + (5 * lensLog);
		minMagnitude = 7.5 + (5 * Math.log10(lensDiameterCm));
		
		
		//return the minimum magnitude 
		return minMagnitude;
	}
	
	//calcMinResolution method
	public double calcMinResolution()
	{
		//calculate the minimum resolution equation
		minResolution = 4.56 / lensDiameter;
		
		//return the minimum resolution
		return minResolution;
	}
	
	
	//calcMaxVisibleMag method 
	public double calcMaxVisibleMag()
	{
		//calculate the maximum visible magnitude equation
		
		maxVisibleMagnification = lensDiameter * 50; 
		
		//return the maximum visible magnification 
		return maxVisibleMagnification;
	}
	
	
	//calcMinVisibleMagnitude method
	public double calcMinVisibleMag()
	{
		//calculate the minimum visible magnitude equation
		minVisibleMagnification = lensDiameter * 4; 
		
		//return the minimum visible magnification
		return minVisibleMagnification;
	}
	
	
	//calcEyepieceMagnification method
	public double calcEyepieceMagnification()
	{
		//get the lens diameter and length of eyepiece in mm
		lensDiameterMm = lensDiameterCm * 10;
		
		//calculate the eyepiece magnification equation
		eyePieceMagnification = lensDiameterMm / eyePieceFocalLength;
		
		
		//return the eyepiece magnification
		return eyePieceMagnification;
	}
	
	
	
	
	

}
