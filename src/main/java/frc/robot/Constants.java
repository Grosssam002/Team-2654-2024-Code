// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class Drivetrain {
    // The left-to-right distance between the drivetrain wheels
    // Should be measured from one center of the wheel to the other.
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(24);
    // The front-to-back distance between the drivetrain wheels.
    // Should be measured from one center of the wheel to the other.
    public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(24);

    //To set the wheels straight set steer offset radians to zero, then go to smart dashboard and read the value for the corresponding wheel. 
    //turn the wheel straight and convert the degrees from smart dashboard to radians using a radians calculator online. 
    //Use that number and replace the zero you inputed with the new number. if its not right try changing the sign in front of the number
    // or you can add a plus or minus Math.PI if it still doesn't work, it may take a couple of tries. try to do one wheel at a time, it will make it easier.
    //If anything happens make sure the ID's are right for each motor and encoder, they are listed below for each module.
    
    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 3;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 4;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 11;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET_RADIANS = 2.808 - Math.PI;

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 2;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 1;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 10;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET_RADIANS = 0.718 ;

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 8;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 13;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET_RADIANS = 2.284 - Math.PI;

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 6;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 5;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 12;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET_RADIANS = 5.607;
}
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int mDriverControllerPort = 1;
  }
}
