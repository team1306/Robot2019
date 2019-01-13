package frc.robot;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.vision.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.*;
import edu.wpi.first.cameraserver.CameraServer;
public class RobotVision implements VisionRunner.Listener<RobotVisionPipeline>
{
// local vars
public VideoCamera camera;
private RobotVisionPipeline vpipe;
private VisionThread vthread;
private final Object vwrite;
public int x;
private double[] y;
private double[] size;
// Initialize Vars
public RobotVision(VideoCamera cams, RobotVisionPipeline vp, Object vw)
{

camera = cams;
vpipe = vp;
vwrite = vw;
vthread = new VisionThread(cams, vp, this);
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

}
// Where Vision Pipeline writes to Robot Vision vars, Synchronized so it doesn't overwrite other variables in main thread
@Override
public void copyPipelineOutputs(RobotVisionPipeline pipe)
{
    synchronized(vwrite)
    {
        this.x = vpipe.width;
       // this.y = vpipe.getY();
        //this.size = vpipe.getSize();
    }


}
public void startThread()
{

    vthread.start();
}

public void captureImage(CameraServer server)
{
    CvSink cvsink;
    Mat image = new Mat();
    cvsink = server.getVideo(this.camera);
    cvsink.setEnabled(true);
    cvsink.grabFrame(image);
    vpipe.process(image);
    cvsink.setEnabled(false);
}

}