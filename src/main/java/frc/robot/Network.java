/**
 * A Class for communicating via network tables 
 * 
 * */
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Network{
    //Constant Names
    final static String gripBlobTable="GRIP/myBlobsReport";

    static Network current=null;
    
    //Class variables
    NetworkTableInstance instance=null;
    public Network(){
        instance=NetworkTableInstance.getDefault();
        current=this;
    }
    public NetworkTable getTable(String name){
        return instance.getTable(name);
    }
    NetworkTableEntry testEntry=null;
    public void testInit(){
        testEntry=getTable(gripBlobTable).getEntry("size");
    }
   
    
}
