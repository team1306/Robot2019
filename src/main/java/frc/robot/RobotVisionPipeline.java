package frc.robot;
import edu.wpi.first.vision.VisionPipeline;
import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
public class RobotVisionPipeline implements VisionPipeline
{
  public int width;  
@Override
public void process(Mat image)
{
width = image.width();
System.out.println(image.width());

}



}