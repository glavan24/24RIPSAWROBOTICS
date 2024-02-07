// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain

;

public class Demo extends Command {
  /** Creates a new Demo. */
  private Timer m_timer = new Timer();
  private final Drivetrain m_drive;
  private final RamseteController m_ramsete = new RamseteController();
  private Trajectory m_trajectory;

  public Demo(Drivetrain drive) {
    System.out.println("Demo(Drivetrain drive)");
        
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    addRequirements(m_drive);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Demo.initialize()");
    m_timer.reset();
    m_timer.start();
    m_trajectory =
    TrajectoryGenerator.generateTrajectory(
        new Pose2d(2.128, 5.089, new Rotation2d()),
        List.of(new Translation2d(6.395, 4.648),
        new Translation2d(2.27, 4.415),
        new Translation2d(2.736, 3.601),
        new Translation2d(3.5356, 4.639),
        new Translation2d(5.238, 4.64),
        new Translation2d(6.57, 3.743),
        new Translation2d(5.535, 4.578),
        new Translation2d(3.481, 4.571),
        new Translation2d(2.539, 3.912)),
        new Pose2d(3.75, 3.0, new Rotation2d()),
        new TrajectoryConfig(2, 2));
    m_drive.resetOdometry(m_trajectory.getInitialPose());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double elapsed = m_timer.get();
    Trajectory.State reference = m_trajectory.sample(elapsed);
    ChassisSpeeds speeds = m_ramsete.calculate(m_drive.getPose(), reference);
    m_drive.drive(speeds.vxMetersPerSecond, speeds.omegaRadiansPerSecond);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
