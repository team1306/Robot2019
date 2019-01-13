package frc.robot;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.vision.*;
public class RobotVision implements VisionRunner.Listener<RobotVisionPipeline>
{
// local vars
public VideoCamera camera;
private RobotVisionPipeline vpipe;
private VisionThread vthread;
private final Object vwrite;
private double[] x;
private double[] y;
private double[] size;
// Initialize Vars
public RobotVision(VideoCamera cams, RobotVisionPipeline vp, Object vw)
{

camera = cams;
vpipe = vp;
vwrite = vw;
vthread = new VisionThread(cams, vp, this);
}
// Where Vision Pipeline writes to Robot Vision vars, Synchronized so it doesn't overwrite other variables in main thread
@Override
public void copyPipelineOutputs(RobotVisionPipeline pipe)
{
    synchronized(vwrite)
    {
        //this.x = vpipe.getX();
       // this.y = vpipe.getY();
        //this.size = vpipe.getSize();
    }


}


}