package tellscopeV4;

public class TeleScopeRefract implements ConstantsInterface {
	
	//constructor for TellsCalculations object
		TeleScopeRefract(double focalRatio, double lensDiameter, double eyePieceFocalLength)
		{
			this.focalRatio = focalRatio;
			this.lensDiameter = lensDiameter;
			this.eyePieceFocalLength = eyePieceFocalLength;
				
		}
		
		
		
		//variables to store focal ratio, main lens, eyepiece focal length
		protected double focalRatio;
		protected double lensDiameter;
		protected double eyePieceFocalLength;
		protected double outerDiameter;
		
		//variables to store calculated results
		protected double focalLength;
		protected double tubeLength;
		protected double distToSecond;
		protected double secondarySizeMinor;
		protected double secondarySizeMajor;
		protected double minMagnitude;
		protected double minResolution;
		protected double maxVisibleMagnification;
		protected double minVisibleMagnification;
		protected double eyePieceMagnification;
		
		//variables to store lens diameter and eyepiece length using different scales
		protected double lensDiameterCm;
		protected double lensDiameterMm;
		
		
		

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
